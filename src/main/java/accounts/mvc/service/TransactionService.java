/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.mvc.service;

import az.kapitalbank.practice.accounts.db.dao.MainDao;
import az.kapitalbank.practice.accounts.db.dao.TransactionDao;
import az.kapitalbank.practice.accounts.mvc.model.Account;
import az.kapitalbank.practice.accounts.mvc.model.Transaction;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 */
@Service
public class TransactionService {
    @Autowired
    @Qualifier("transaction")
    private TransactionDao dao;

    public List<Transaction> getTransaction() {
        return dao.list();
    }
    public boolean cancel(Transaction t) {

        return dao.cancelTransfer(t);
    }
}
