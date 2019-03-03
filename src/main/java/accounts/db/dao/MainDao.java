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
import az.kapitalbank.practice.accounts.mvc.model.Validation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 * @param <T>
 */
public interface MainDao<T> {

    boolean save(T t);

    Validation check(T t);

    boolean edit(T t);

    void checkAccount(AccJson a) throws AccountNotFound, SQLException;
    
    void checkPan(AccJson a) throws AccountNotFound, SQLException;

    void checkAmount(AccJson a) throws AccountNotFound, SQLException;

    boolean transfer(AccJson acc);

    boolean delete(T t);

    List<T> list();

    Account selectAccount(int id);
}
