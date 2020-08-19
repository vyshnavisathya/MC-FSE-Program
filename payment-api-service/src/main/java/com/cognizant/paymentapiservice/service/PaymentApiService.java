package com.cognizant.paymentapiservice.service;


import com.cognizant.paymentapiservice.model.Card;
import com.cognizant.paymentapiservice.model.Payment;

import java.util.List;

public interface PaymentApiService {

    public List<Payment> listAllPayments();

    public Object savePaymentStatus(Payment payment);

    public Payment updatePaymentStatus(Payment payment);

    public void delete(Integer id);

    public Card fetchCardDetailsByFeignClient(Integer id);

    default Card findCachedRatingByCustomerId(Integer id) {
        return null;
    }
}

