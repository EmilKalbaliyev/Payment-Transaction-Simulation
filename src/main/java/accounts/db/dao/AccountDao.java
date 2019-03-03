/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.db.dao;

import az.kapitalbank.practice.accounts.mvc.model.AccJson;
import az.kapitalbank.practice.accounts.mvc.model.Account;
import az.kapitalbank.practice.accounts.mvc.model.AccountNotFound;
import az.kapitalbank.practice.accounts.mvc.model.Card;
import az.kapitalbank.practice.accounts.mvc.model.Person;
import az.kapitalbank.practice.accounts.mvc.model.Validation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 */
@Repository("account")
public class AccountDao implements MainDao<Account> {

    @Autowired
    private Connection connection;

    @Override
    public Validation check(Account a) {
        Validation valid = new Validation();
        try {
            Person p = a.getOwner();

            String sql = "select * from person where pin=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, p.getPin());

            ResultSet rs = statement.executeQuery();
            if (rs.isBeforeFirst()) {
                valid.setIsValid(false);
                valid.setMsj("Person already exists");
            } else {
                valid.setIsValid(true);
                valid.setMsj("Account successfully added");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return valid;
    }

    @Override
    public void checkAccount(AccJson a) throws AccountNotFound, SQLException {

        String sql = "select count(id) as cnt from account where id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, a.getAcc_id());
        ResultSet rs = statement.executeQuery();
        if (rs.next() && rs.getInt("cnt") == 0) {
            throw new AccountNotFound("Account is not found.");
        }
    }

    @Override
    public void checkPan(AccJson a) throws AccountNotFound, SQLException {

        String sql = "select count(id) as cnt from card where pan=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, a.getPAN());
        ResultSet rs = statement.executeQuery();
        if (rs.next() && rs.getInt("cnt") == 0) {
            throw new AccountNotFound("Card is not found.");
        }
    }

    @Override
    public void checkAmount(AccJson a) throws AccountNotFound, SQLException {

        String sql = "select case when amount>=? then 1 else 0 end as exist from account where  id = (select id from card where pan= ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, a.getAmount());
        statement.setLong(2, a.getPAN());
        ResultSet rs = statement.executeQuery();
        if (rs.next() && rs.getInt("exist") == 0) {
            throw new AccountNotFound("Not enough amount.");//TODO prettify msg
        }

    }

    public boolean transfer(AccJson a) {
        try {
            String sql = "";
            PreparedStatement statement;
            sql = "update Account set amount = amount - ? where id = (select id from card where pan = ? )";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, a.getAmount());
            statement.setLong(2, a.getPAN());
            statement.execute();

            sql = "update Account set amount = amount + ? where id = ? ";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, a.getAmount());
            statement.setString(2, a.getAcc_id());
            statement.execute();
            
            sql= "insert into transaction(fromAcc, toAcc, amount, time_stamp, status, status_date) values((select acc_id from card where pan = ?), ?, ?, sysdate, 'Active', sysdate)";
            statement = connection.prepareStatement(sql);
            statement.setLong(1, a.getPAN());
            statement.setString(2, a.getAcc_id());
            statement.setInt(3, a.getAmount());
            statement.execute();
            
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    
    @Override
    public boolean save(Account a) {
        try {
            String sql = "insert into person(name, surname, patronymic, pin) values(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            Person p = a.getOwner();
            statement.setString(1, p.getName());
            statement.setString(2, p.getSurname());
            statement.setString(3, p.getPatronymic());
            statement.setString(4, p.getPin());
            statement.execute();

            sql = "insert into Account(person_id,amount) values( (select id from person where pin =?),?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, p.getPin());
            statement.setInt(2, a.getAmount());
            statement.execute();

//            sql = "update table Account set person_id = select id from person where pin = ?";
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, p.getPin());
//            statement.execute();
            for (Card card : a.getCards()) {
                sql = "insert into Card(acc_id, name, surname, exp_date, pan, dd) values((select id from account where person_id=(select id from person where pin=?)),?, ?, TO_DATE(?,'DD/MM/YYYY'), ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, p.getPin());
                statement.setString(2, card.getName());
                statement.setString(3, card.getSurname());
                statement.setString(4, card.getEXP_DATE());
                statement.setString(5, card.getPAN());
                statement.setString(6, card.getDiscreationaryData());
                statement.execute();
            }
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean edit(Account a) {
        try {
            String sql = "";
//            String sql = "update person set name=?, surname=?, patronymic=?, pin=? where id=(select a.person_id from account a where a.id =?)";
            PreparedStatement statement;
            Person p = a.getOwner();
//            statement.setString(1, p.getName());
//            statement.setString(2, p.getSurname());
//            statement.setString(3, p.getPatronymic());
//            statement.setString(4, p.getPin());
//            statement.setInt(5, a.getId());
//            statement.execute();
            if (check(a).isIsValid()) {
                sql = "insert into person(name, surname, patronymic, pin) values(?, ?, ?, ?)";
                statement = connection.prepareStatement(sql);
                statement.setString(1, p.getName());
                statement.setString(2, p.getSurname());
                statement.setString(3, p.getPatronymic());
                statement.setString(4, p.getPin());
                statement.execute();
            }
            sql = "update Account set amount=? id =(select a.person_id from account a where a.id =?) where id=?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, a.getAmount());
            statement.setString(2, p.getPin());
            statement.setInt(3, a.getId());
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public boolean delete(Account a) {
        try {
            String sql = "delete from card where acc_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, a.getId());
            statement.execute();
            sql = "delete from Account where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, a.getId());
            statement.execute();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    @Override
    public List<Account> list() {
        List<Account> accounts = new ArrayList<>();

        Account acc;

        try {
            String sql = "select a.id, p.name, p.surname, p.patronymic, a.amount from person p INNER JOIN account a on p.id=a.person_id";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
//                List<Card> cards = new ArrayList<>();
                Person per = new Person();
                per.setId(rs.getInt("id"));
                per.setName(rs.getString("name"));
                per.setSurname(rs.getString("surname"));
                per.setPatronymic(rs.getString("patronymic"));
//                Card card = new Card();
//                card.setPAN(rs.getString("pan"));
//                card.setId(rs.getInt("card_id"));
//                cards.add(card);
                acc = new Account();
                acc.setOwner(per);
                acc.setId(rs.getInt("id"));
                acc.setAmount(rs.getInt("amount"));

//                acc.setCards(cards);
                accounts.add(acc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    public Account selectAccount(int id) {
        Account acc = new Account();
        try {
            String sql = "select p.id as PID,"
                    + " p.name,"
                    + " p.surname,"
                    + " p.patronymic,"
                    + "p.pin,"
                    + "a.id as AID,"
                    + " a.amount"
                    + " from person p"
                    + " INNER JOIN account a on p.id=a.person_id"
                    + " where a.id=?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Person per = new Person();
                per.setId(rs.getInt("PID"));
                per.setName(rs.getString("name"));
                per.setSurname(rs.getString("surname"));
                per.setPatronymic(rs.getString("patronymic"));
                per.setPin(rs.getString("pin"));
                acc.setId(rs.getInt("AID"));
                acc.setOwner(per);
                acc.setAmount(rs.getInt("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acc;
    }
}
