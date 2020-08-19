package com.cognizant.paymentapiservice.cardservice;


import com.cognizant.paymentapiservice.model.Card;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="card-service",
        url = "localhost:8082"
)
@RibbonClient(name="card-service")
@FunctionalInterface
public interface CardServiceProxy {
    @GetMapping("/cards/{customerId}")
    public Card fetchCardDetailsbyCutomerId(@RequestParam("customerId") Integer customerId);

}
