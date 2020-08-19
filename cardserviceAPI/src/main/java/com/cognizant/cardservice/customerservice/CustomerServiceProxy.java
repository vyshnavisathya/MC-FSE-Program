package com.cognizant.cardservice.customerservice;


import com.cognizant.cardservice.model.Customer;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-service-api")
@RibbonClient(name="customer-service-api")
public interface CustomerServiceProxy {
    @GetMapping("/customers/{id}")
    public Customer getOneCustomer(@PathVariable("id") Integer id);
}
