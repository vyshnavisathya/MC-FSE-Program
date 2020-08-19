package com.cognizant.paymentapiservice.cardservice;

import com.cognizant.paymentapiservice.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardServiceFallback implements CardServiceProxy {

    @Override
    public Card fetchCardDetailsbyCutomerId(Integer customerId) {
        System.out.println("Exception");
        throw new RuntimeException();
    }
}
