package com.cognizant.customer.dao;

import com.cognizant.customer.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CustomerDAO {
    private static List<Customer> customers = new ArrayList<Customer>();
    private static int customerCount=3;

    public List<Customer> findAll(){
        return customers;
    }

    public Customer save(Customer customer){
        if(customer.getId() == null) {
            customer.setId(++customerCount);
        }
        customers.add(customer);
        return customer;
    }

    public Customer findOne(int id){
        for (Customer customer:customers) {
            if(customer.getId()==id){
                return customer;
            }
        }
        return null;
    }
    public Customer deleteById(int id){
        Iterator<Customer> iterator = customers.iterator();
        while(iterator.hasNext()){
            Customer customer = iterator.next();
            if(customer.getId()==id){
                iterator.remove();
                return customer;
            }
        }
        return null;
    }
 }
