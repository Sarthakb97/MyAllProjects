package com.example.customerapi.Service;

import com.example.customerapi.Model.Customer;
import com.example.customerapi.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    //add new customer into Database Table
    public void addCustomer(Customer c)
    {
        repository.save(c);
    }

    //get all customer
    public List<Customer> allCustomer() {
        List<Customer> customerList=repository.findAll();
        return customerList;
    }

    //get customer by id
    public Customer getCustomerById(int id)
    {
        return repository.findById(id).orElse(null);
    }

    //get customer by name
    public List<Customer> getCustomerByName(String name)
    {
        return repository.findByCustomerName(name);
    }
    //get customer by email
    public List<Customer> getCustomerByEmail(String email)
    {
        return repository.findByCustomerEmail(email);
    }
    //get customer having age greater than ?(user)
    public List<Customer> getCustomerByAge(int age)
    {
        return repository.findByCustomerAgeGreaterThan(age);
    }

    //get customer having purchase less than? (user)
    public List<Customer> getTotalPurchaseAmtLesserThan(double amt)
    {
        return repository.findByTotalPurchaseAmtLessThan(amt);
    }

    //JPQL Name
    public List<Customer> getviewCustomer(String name)
    {
        return repository.viewCustomer(name);
    }

    //JPQL Age And Amt
    public List<String> getshowCustomer(int age, double amt)
    {
        return repository.showCustomer(age, amt);
    }

    //
    public List<String> displayCustomerNameContains(String name)
    {
        return repository.displayCustomerNameContains(name);
    }

    //
    public List<String> displaySpecificCharStart(String name)
    {
        return repository.displaySpecificCharStart(name);
    }

    //
    public List<String> displaySpecificCharEnd(String name)
    {
        return repository.displaySpecificCharEnd(name);
    }
}
