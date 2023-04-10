package com.example.customerapi.controller;

import com.example.customerapi.Model.Customer;
import com.example.customerapi.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @PostMapping("/customer")
    public void addCustomer(@RequestBody Customer c)
    {
        service.addCustomer(c);
    }
    @GetMapping("allcustomer")
    public List<Customer> getAllCustomer()
    {
        return service.allCustomer();
    }

    @GetMapping("/customerbyid")
    public Customer getCustomerById(@RequestParam int id)
    {
        return service.getCustomerById(id);
    }

    @GetMapping("/customerbyname")
    public List<Customer> getCustomerByName(@RequestParam String name)
    {
        return service.getCustomerByName(name);
    }

    @GetMapping("/customerbyemail")
    public List<Customer> getCustomerByEmail(@RequestParam String email)
    {
        return service.getCustomerByEmail(email);
    }

    @GetMapping("/customerbyage")
    public List<Customer> getCustomerAgeGraterThan(@RequestParam int age)
    {
        return service.getCustomerByAge(age);
    }

    @GetMapping("/totalpurchaseamtlesserthan")
    public List<Customer> getTotalPurchaseAmtLesserThan(@RequestParam double amt)
    {
        return service.getTotalPurchaseAmtLesserThan(amt);
    }

    @GetMapping("/customer1")
    public List<Customer> viewCustomer(String name)
    {
        return service.getviewCustomer(name);
    }

    @GetMapping("/customer2")
    public List<String> showCustomer(int age, double amt)
    {
        return service.getshowCustomer(age,amt);
    }

    @GetMapping("/customer3")
    public List<String> displayCustomerNameContains(String name)
    {
        return service.displayCustomerNameContains(name);
    }

    @GetMapping("/customer4")
    public List<String> displaySpecificCharStart(String name)
    {
        return service.displaySpecificCharStart(name);
    }
    @GetMapping("/customer5")
    public List<String> displaySpecificCharEnd(String name)
    {
        return service.displaySpecificCharEnd(name);
    }
}
