package com.cognizant.cardservice.service;


import com.cognizant.cardservice.entity.Card;
import com.cognizant.cardservice.model.Customer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CardService {

    public List<Card> listAllCustomers();

    public Card getOneCustomerCardDetails(Integer customerId);

    public ResponseEntity<Customer> getCustomerDetails(int id);

    public Object saveCardDetails(Card card);

    public void updateCardDetails(Card card);

    void deleteCardDetails(Integer id);
}
