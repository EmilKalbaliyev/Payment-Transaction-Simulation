/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.mvc.service;

import az.kapitalbank.practice.accounts.mvc.model.Account;
import az.kapitalbank.practice.accounts.mvc.model.Card;
import az.kapitalbank.practice.accounts.mvc.model.Validation;
import java.util.List;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 */
public interface MainService<T> {

    List<T> getAccounts();

    boolean save(T a);
    boolean edit(T t);
    boolean delete(T t);
    Validation check(T t);
    Account selectAccount(int id);
    
}
