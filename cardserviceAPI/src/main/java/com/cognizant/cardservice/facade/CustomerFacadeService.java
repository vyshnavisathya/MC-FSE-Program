package com.cognizant.cardservice.facade;

import com.cognizant.cardservice.model.Customer;

@FunctionalInterface
public interface CustomerFacadeService {
    public Customer fetchCustomer(Integer id);
}
