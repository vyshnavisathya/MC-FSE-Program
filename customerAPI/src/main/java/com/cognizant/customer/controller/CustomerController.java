package com.cognizant.customer.controller;
import com.cognizant.customer.model.Customer;
import com.cognizant.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @GetMapping(path="/customers",  produces = "application/json")
    public List<Customer> getAllCustomers(){
        return customerService.listAllCustomers();
    }

    /* for feign client */
    @GetMapping("/customers/{id}")
    public Customer getOneCustomer(@PathVariable Integer id) {
        return  customerService.getCustomer(id);
    }

    @PostMapping(path = "/customers")
    public ResponseEntity<Object> createCustomerData(@Valid @RequestBody Customer customer){
        customerService.saveCustomerDetails(customer);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();

        //Send location in response
        return ResponseEntity.created(location).build();
    }


    @PutMapping(path = "/customers")
    public void updateCustomerData(@Valid @RequestBody Customer customer){
        customerService.updateCustomerDetails(customer);
    }
}
