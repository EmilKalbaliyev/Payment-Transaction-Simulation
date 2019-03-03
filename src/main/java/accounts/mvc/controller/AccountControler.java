/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accounts.mvc.controller;

import az.kapitalbank.practice.accounts.mvc.model.Account;
import az.kapitalbank.practice.accounts.mvc.model.AccountView;
import az.kapitalbank.practice.accounts.mvc.model.Card;
import az.kapitalbank.practice.accounts.mvc.model.EditView;
import az.kapitalbank.practice.accounts.mvc.model.Person;
import az.kapitalbank.practice.accounts.mvc.model.Validation;
import az.kapitalbank.practice.accounts.mvc.service.MainService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 */
@Controller
@RequestMapping("/account")
public class AccountControler {

    @Autowired
    private MainService service;

    @GetMapping("/index")
    public ModelAndView welcome() {
        ModelAndView mav = new ModelAndView("welcome");
        mav.addObject("accounts", service.getAccounts());  //attribute name, attribute value
        return mav;
    }

    @GetMapping("/welcome")
    public ModelAndView welcome(@RequestParam("msj") String message) {
        ModelAndView mav = new ModelAndView("welcome");
        mav.addObject("accounts", service.getAccounts());
        mav.addObject("msj", message);
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("add");
        mav.addObject("account", new AccountView());
        return mav;
    }

    @PostMapping(value = "/add")
    public Object save(@ModelAttribute AccountView acc, RedirectAttributes redirectAttributes) {

        Person person = new Person();
        person.setName(acc.getName());
        person.setSurname(acc.getSurname());
        person.setPatronymic(acc.getPatronymic());
        person.setPin(acc.getPin());
        Account account = new Account();
        account.setId(acc.getId());
        account.setAmount(acc.getAmount());
        account.setOwner(person);
        Card card = new Card();
        card.setPAN(acc.getPan());
        card.setName(acc.getOwnerName());
        card.setSurname(acc.getOwnerSurname());
        card.setEXP_DATE(acc.getExpDate());
        card.setDiscreationaryData(acc.getDiscreationaryData());
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        account.setCards(cards);
        Validation v = service.check(account);
        if (v.isIsValid()) {
            service.save(account);
            redirectAttributes.addAttribute("msj", v.getMsj());
            return new RedirectView("welcome"); //check
        } else {
            ModelAndView mav = new ModelAndView("add");
            mav.addObject("account", acc);
            mav.addObject("msj", v.getMsj());
            return mav;
        }
    }
    // --  DELETE -- 

    @GetMapping(value = "/delete/{id}")
    public Object delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Account acc = service.selectAccount(id);
        Person p = acc.getOwner();
        EditView editv = new EditView();
        editv.setName(p.getName());
        editv.setSurname(p.getSurname());
        editv.setPatronymic(p.getPatronymic());
        editv.setPin(p.getPin());
        editv.setId(acc.getId());
        editv.setAmount(acc.getAmount());
        ModelAndView mav = new ModelAndView("delete");
        mav.addObject("account", editv);
        return mav;
    }

    @PostMapping(value = "/delete")
    public Object delete(@ModelAttribute EditView acc, RedirectAttributes redirectAttributes) {

        Person person = new Person();
        person.setName(acc.getName());
        person.setSurname(acc.getSurname());
        person.setPatronymic(acc.getPatronymic());
        person.setPin(acc.getPin());
        Account account = new Account();
        account.setId(acc.getId());
        account.setAmount(acc.getAmount());
        account.setOwner(person);
        service.delete(account);
        redirectAttributes.addAttribute("msj", "Successfully deleted");
        return new RedirectView("welcome"); //check
    }

    //  --  EDIT  --
    @GetMapping(value = "/edit/{id}")
    public Object edit(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Account acc = service.selectAccount(id);
        Person p = acc.getOwner();
        EditView editv = new EditView();
        editv.setName(p.getName());
        editv.setSurname(p.getSurname());
        editv.setPatronymic(p.getPatronymic());
        editv.setPin(p.getPin());
        editv.setId(acc.getId());
        editv.setAmount(acc.getAmount());
        ModelAndView mav = new ModelAndView("edit");
        mav.addObject("account", editv);
        return mav;

    }

    @PostMapping(value = "/edit")
    public Object edit(@ModelAttribute EditView acc, RedirectAttributes redirectAttributes) {

        Person person = new Person();
        person.setName(acc.getName());
        person.setSurname(acc.getSurname());
        person.setPatronymic(acc.getPatronymic());
        person.setPin(acc.getPin());
        Account account = new Account();
        account.setId(acc.getId());
        account.setAmount(acc.getAmount());
        account.setOwner(person);
        service.edit(account);
        redirectAttributes.addAttribute("msj", "Successfully edited");
        return new RedirectView("welcome"); //check
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView mav = new ModelAndView("login");
        if (error != null) {
            mav.addObject("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            mav.addObject("message", "You have been logged out successfully.");
        }

        return mav;
    }
        @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDenied(Model model, String error, String logout) {
        
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "accessDenied";
    }
}
