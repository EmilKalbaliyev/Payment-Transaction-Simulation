/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.mvc.service;

import az.kapitalbank.practice.accounts.db.dao.MainDao;
import az.kapitalbank.practice.accounts.mvc.model.Account;
import az.kapitalbank.practice.accounts.mvc.model.Card;
import az.kapitalbank.practice.accounts.mvc.model.Validation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements MainService<Account> {

    @Autowired
    @Qualifier("account")
    private MainDao<Account> dao;

    @Override
    public List<Account> getAccounts() {
        return dao.list();
    }

    @Override
    public boolean save(Account a) {

        return dao.save(a);
    }

    @Override
    public boolean edit(Account a) {

        return dao.edit(a);
    }

    @Override
    
    public boolean delete(Account a) {

        return dao.delete(a);
    }

    @Override
    public Validation check(Account a) {

        return dao.check(a);
    }

    @Override
    public Account selectAccount(int id) {

        return dao.selectAccount(id);
    }

}
