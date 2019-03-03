/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.mvc.controller;


import az.kapitalbank.practice.accounts.mvc.model.Transaction;
import az.kapitalbank.practice.accounts.mvc.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 */
@Controller
@RequestMapping("/transaction")
public class TransactionController {
    
    @Autowired
    private TransactionService tservice;

    @GetMapping("/index")
    public ModelAndView welcome() {
        ModelAndView mav = new ModelAndView("transaction");
        mav.addObject("transaction", tservice.getTransaction());
        return mav;
    }

    @GetMapping("/cancel/{id}")
    public Object cancel(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Transaction t = new Transaction();
        t.setId(id);
        tservice.cancel(t);
        return new RedirectView("/transaction/index"); //check
    }
}
