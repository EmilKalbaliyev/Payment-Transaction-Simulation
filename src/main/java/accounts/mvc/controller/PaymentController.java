package accounts.mvc.controller;

import az.kapitalbank.practice.accounts.mvc.model.AccJson;
import az.kapitalbank.practice.accounts.mvc.model.ResponseJson;
import az.kapitalbank.practice.accounts.mvc.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mardan Safarov & Emil Kalbaliyev
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @PostMapping("/test")
    public ResponseJson test(@RequestBody AccJson json) {
        return service.transfer(json);
    }
}
