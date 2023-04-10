package com.example.customerapi.Repository;

import com.example.customerapi.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    // returns the list of customer by name
    List<Customer> findByCustomerName(String name);

    // returns the list of customer by email
    List<Customer> findByCustomerEmail(String email);

    //returns the list of customer having age greater than specific value
    List<Customer> findByCustomerAgeGreaterThan(int age);

    //returns the list of customer having purchase amt lesser than specific value
    List<Customer> findByTotalPurchaseAmtLessThan(double amt);

    // returns the list of customer having name=? and age=?

    //return the list of customer having age between ? and ?

    //writing JPQL
    //write JPQL to fetch customer by Name
    @Query("select c from Customer c where c.customerName=:name")
    List<Customer> viewCustomer(@Param("name") String name);

    //write JPQl to fetch customer names having age<? and amt>=?
    @Query("select c.customerName from Customer c where c.customerAge<:age and c.totalPurchaseAmt>=:amt")
    List<String> showCustomer(@Param("age") int age,@Param("amt") double amt);

    //write JPQL to fetch Customer Names whose contains given characters
    @Query("select c.customerName from Customer c where c.customerName like %:name%")
    List<String> displayCustomerNameContains(@Param("name") String name);

    //write JPQL to fetch customer Names whose name starts with specific Character
    @Query("select c.customerName from Customer c where c.customerName like :name%")
    List<String> displaySpecificCharStart(@Param("name") String name);

    //write JPQL to fetch customer Names whose name ends with specific Character
    @Query("select c.customerName from Customer c where c.customerName like %:name")
    List<String> displaySpecificCharEnd(@Param("name") String name);
}
