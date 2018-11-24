package de.tarent.challenge.store.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    public CustomerService service;


    @GetMapping("/list")
    public List<Customer> list(){
        return service.list();
    }

}
