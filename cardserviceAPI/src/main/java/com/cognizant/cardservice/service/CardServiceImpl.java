package com.cognizant.cardservice.service;


import com.cognizant.cardservice.dao.CardServiceDao;
import com.cognizant.cardservice.entity.Card;
import com.cognizant.cardservice.exception.CustomerNotFoundException;
import com.cognizant.cardservice.model.Customer;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class CardServiceImpl implements CardService{

    private static final Logger LOG = LoggerFactory.getLogger(CardServiceImpl.class);


    @Autowired
    private CardServiceDao cardServiceDao;

    @Autowired
    private EurekaClient eurekaClient;

    @Value("${service.customersearch.serviceId}")
    private String customerSearchServiceId;

    @Autowired
    public RestTemplate restTemplate;

    @Override
    public List<Card> listAllCustomers() {
        return cardServiceDao.findAll();
    }

    @Override
    public Card getOneCustomerCardDetails(Integer customerId) {
        LOG.debug("request : {}", cardServiceDao.findByCustomerId(customerId));
        return cardServiceDao.findByCustomerId(customerId);
    }

    @Override
    public ResponseEntity<Customer> getCustomerDetails(int id) {
        Application application = eurekaClient.getApplication(customerSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort()  + "/customers/" +id;
        return restTemplate.getForEntity(url,Customer.class);
    }

    @Override
    public Object saveCardDetails(Card card) {
        Card insertCardDetails = new Card();
        Card savedCardDetails = null;
        //move into the if block
        ResponseEntity<Customer> getCustomerDetails = this.getCustomerDetails(card.getCustomerId());
        if(getCustomerDetails.getBody().getId() == insertCardDetails.getCustomerId()) {
            insertCardDetails.setCardNumber(card.getCardNumber());
            insertCardDetails.setExpiryDate(card.getExpiryDate());
            insertCardDetails.setCardType(card.getCardType());
            insertCardDetails.setCustomerId(card.getCustomerId());
            savedCardDetails = cardServiceDao.save(insertCardDetails);
        } else{
            throw new CustomerNotFoundException("Customer is not available");
        }
        return  savedCardDetails;
    }

    @Override
    public void updateCardDetails(Card card) {
        Card updateCardDetails = new Card();
        ResponseEntity<Customer> getCustomerDetails = this.getCustomerDetails(updateCardDetails.getCustomerId());
        if (getCustomerDetails.getBody().getId() == updateCardDetails.getCustomerId()){
            updateCardDetails.setCustomerId(card.getCustomerId());
            updateCardDetails.setCardType(card.getCardType());
            updateCardDetails.setCardNumber(card.getCardNumber());
            updateCardDetails.setExpiryDate(card.getExpiryDate());
            cardServiceDao.updateCard(updateCardDetails.getCardNumber(),updateCardDetails.getCustomerId());
        } else{
            throw new CustomerNotFoundException("Card Details are not valid");
        }
    }

    @Override
    public void deleteCardDetails(Integer id) {
        cardServiceDao.deleteById(id);
    }
}

