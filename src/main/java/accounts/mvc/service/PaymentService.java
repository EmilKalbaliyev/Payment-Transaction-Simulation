/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.mvc.service;

import az.kapitalbank.practice.accounts.db.dao.MainDao;
import az.kapitalbank.practice.accounts.mvc.model.AccJson;
import az.kapitalbank.practice.accounts.mvc.model.Account;
import az.kapitalbank.practice.accounts.mvc.model.AccountNotFound;
import az.kapitalbank.practice.accounts.mvc.model.ResponseJson;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 */
@Service
public class PaymentService {

    @Autowired
    @Qualifier("account")
    private MainDao<Account> dao;

    public ResponseJson transfer(AccJson a) {

        ResponseJson rj = new ResponseJson();
        try {
            //check availability
            dao.checkAccount(a);
            dao.checkPan(a);
            dao.checkAmount(a);

            //if all is ok then transfer
            dao.transfer(a);

            //transfer is ok
            rj.setSuccess(true);
            rj.setMessage("Payment successful");
        } catch (Exception ex) {

            //log
            Logger.getLogger(PaymentService.class.getName()).log(Level.SEVERE, null, ex);

            //response
            rj.setSuccess(false);
            rj.setMessage(ex.getMessage());
        }

        //return response
        return rj;
    }

}
