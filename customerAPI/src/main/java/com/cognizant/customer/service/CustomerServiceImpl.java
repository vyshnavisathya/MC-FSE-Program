package com.cognizant.customer.service;


import com.cognizant.customer.exception.CustomerNotFoundException;
import com.cognizant.customer.model.Customer;
import com.cognizant.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{


    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> listAllCustomers() {
        //sort it based on ID using stream API

        List<Customer> list = customerRepository.findAll();

//        List<Customer> sortedList = list.stream().sorted().collect(Collectors.toList());
//        System.out.println(sortedList);
        return list;
    }

    @Override
    public Customer getCustomer(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    @Override
    public Object saveCustomerDetails(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void updateCustomerDetails(Customer customer) {
        customerRepository.updateCustomer(customer.getName(),customer.getId());
    }
}
