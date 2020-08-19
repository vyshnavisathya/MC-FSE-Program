package com.cognizant.paymentapiservice.service;

import com.cognizant.paymentapiservice.cardservice.CardServiceProxy;
import com.cognizant.paymentapiservice.exception.CustomerNotFoundException;
import com.cognizant.paymentapiservice.model.Card;
import com.cognizant.paymentapiservice.model.Payment;
import com.cognizant.paymentapiservice.model.Status;
import com.cognizant.paymentapiservice.repository.PaymentApiRepository;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class PaymentApiServiceImpl implements PaymentApiService {

    HashMap<Integer, Card> map = new HashMap<Integer, Card>();

    @Autowired
    private PaymentApiRepository paymentApiRepository;

    @Autowired
    private CardServiceProxy cardServiceProxy;

    @Autowired
    private EurekaClient eurekaClient;

    @Override
    public List<Payment> listAllPayments() {
        System.out.println(map);
        return paymentApiRepository.findAll();
    }

//create a class level variable with hashmap of type Integer,Card
    @Override
    public Card fetchCardDetailsByFeignClient(Integer id) {
        Card card = cardServiceProxy.fetchCardDetailsbyCutomerId(id);
        map.put(id, card);
        System.out.println(map);
        return card;
    }

    @Override
    public Card findCachedRatingByCustomerId(Integer id){
        Card cardDetails =  new Card();
        System.out.println(map);
        //just get value to that key passing in the ID
        //Sometimes stream API reduces performance
        //avoid using class level variables - we can use pojo's have an update method
        Iterator hmIterator = map.entrySet().iterator();
        while (hmIterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry)hmIterator.next();
            if(mapElement.getKey().equals(id))
                cardDetails = ((Card) mapElement.getValue());
            System.out.println(mapElement.getKey() + " : " + cardDetails);
        }
        return cardDetails;
    }


    @Override
    public Object savePaymentStatus(Payment payment) {
        Card getCardDetails = this.fetchCardDetailsByFeignClient(payment.getCustomerId());
        Payment p1 = new Payment();
        if(payment.getCustomerId().equals(getCardDetails.getCustomerId()) && (payment.getCardNumber().equals(getCardDetails.getCardNumber())) && (payment.getCardType().equals(getCardDetails.getCardType()))
                    && (payment.getExpiryDate().equals(getCardDetails.getExpiryDate()))){
                if(payment.getStatus().equals(Status.Closed)) {
                    try{
                        p1.setStatus(Status.Open);
                        p1 = paymentApiRepository.save(payment);
                    } catch(Exception e){
                        e.printStackTrace();
                    }

                } else{
                    payment.setStatus(Status.Failure);
                }
            }
         else {
                throw new CustomerNotFoundException("Customer is not available");
            }
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p1.getId()).toUri();
            return ResponseEntity.created(location).build();

    }

    @Override
    public Payment updatePaymentStatus(Payment payment) {
        Card getCardDetails = this.fetchCardDetailsByFeignClient(payment.getCustomerId());
        if(payment.getCustomerId().equals(getCardDetails.getCustomerId()) && (payment.getCardNumber().equals(getCardDetails.getCardNumber())) && (payment.getCardType().equals(getCardDetails.getCardType()))
                && (payment.getExpiryDate().equals(getCardDetails.getExpiryDate())) && (payment.getStatus() == Status.Open)){
                    payment.setStatus(Status.Closed);
            } else{
            throw new CustomerNotFoundException("Customer is not available");
        }
        return paymentApiRepository.save(payment);
    }

    @Override
    public void delete(Integer id) {
        paymentApiRepository.deleteById(id);
    }
}
