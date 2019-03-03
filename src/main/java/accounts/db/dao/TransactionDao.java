/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.db.dao;

import az.kapitalbank.practice.accounts.mvc.model.Transaction;
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

@Repository("transaction")
public class TransactionDao {
     @Autowired
    private Connection connection;

    public List<Transaction> list() {
        
        List<Transaction> transaction = new ArrayList<>();

        Transaction tr;

        try {
            String sql = "select id, fromAcc, toAcc, amount, time_stamp, status, status_date from transaction";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                tr = new Transaction();
                tr.setId(rs.getInt("id"));
                tr.setFrom(rs.getInt("fromAcc"));
                tr.setTo(rs.getInt("toAcc"));
                tr.setAmount(rs.getInt("amount"));
                tr.setDate(rs.getString("time_stamp"));
                tr.setStatus(rs.getString("status"));
                tr.setStatus_date(rs.getString("status_date"));

                transaction.add(tr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }
    
    public boolean cancelTransfer(Transaction t) {
        try {
            String sql = "";
            PreparedStatement statement;

            sql = "update Account set amount = amount - (select amount from transaction where id =?) where id = (select toAcc from transaction where id =?)";  
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            statement.setInt(2, t.getId());

            statement.execute();

            sql = "update Account set amount = amount + (select amount from transaction where id =?) where id = (select fromAcc from transaction where id =?)";  
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            statement.setInt(2, t.getId());

            statement.execute();
            
            sql= "update transaction set status = 'Canceled', status_date = sysdate where id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, t.getId());
            statement.execute();
            
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

}
