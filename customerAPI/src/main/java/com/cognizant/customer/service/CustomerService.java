package com.cognizant.customer.service;

import com.cognizant.customer.model.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> listAllCustomers();

    public Customer getCustomer(int id);

    public Object saveCustomerDetails(Customer customer);

    public void updateCustomerDetails(Customer customer);


}
