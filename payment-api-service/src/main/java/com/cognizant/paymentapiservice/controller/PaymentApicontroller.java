package com.cognizant.paymentapiservice.controller;


import com.cognizant.paymentapiservice.cardservice.CardServiceProxy;
import com.cognizant.paymentapiservice.model.Card;
import com.cognizant.paymentapiservice.model.Payment;
import com.cognizant.paymentapiservice.repository.PaymentApiRepository;
import com.cognizant.paymentapiservice.service.PaymentApiService;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaymentApicontroller {

    @Autowired
    private PaymentApiService paymentApiService;

    @Autowired
    private CardServiceProxy cardServiceProxy;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private PaymentApiRepository paymentApiRepository;

    @HystrixCommand(fallbackMethod = "findCachedRatingById", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    @GetMapping(value = "/template/payments-feign/{id}")
    public Card getCardDetailsFeign(@PathVariable Integer id) {
        return paymentApiService.fetchCardDetailsByFeignClient(id);
    }

    public Card findCachedRatingById(Integer id){
        return paymentApiService.findCachedRatingByCustomerId(id);

    }



    @GetMapping("/payments")
    public List<Payment> list() {
        return paymentApiService.listAllPayments();
    }

    /* Api to maintain tolerance needs work */
    @GetMapping("/payments-tolerance")
    public List<Payment> listUnderTolerance() {
        return paymentApiRepository.findAll();
    }

    @PostMapping("/initiatepaymentstatus")
    public ResponseEntity<Object> savePaymentDetails(@RequestBody Payment payment) {
        return (ResponseEntity<Object>) paymentApiService.savePaymentStatus(payment);
    }

    @PutMapping("paymentstatus")
    public Payment updatePaymentDetails(@RequestBody Payment payment) {
        return paymentApiService.updatePaymentStatus(payment);
    }
}
