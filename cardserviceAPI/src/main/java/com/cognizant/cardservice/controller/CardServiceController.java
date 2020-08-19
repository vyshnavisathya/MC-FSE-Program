package com.cognizant.cardservice.controller;

import com.cognizant.cardservice.customerservice.CustomerServiceProxy;
import com.cognizant.cardservice.entity.Card;
import com.cognizant.cardservice.model.Customer;
import com.cognizant.cardservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class CardServiceController {


    @Autowired
    private CustomerServiceProxy customerServiceProxy;

    @Autowired
    private CardService cardService;


    @GetMapping(path = "/cards")
    public List<Card> getCard(){
        return cardService.listAllCustomers();
    }

    /* to use in payment service api*/
    @GetMapping("/cards/{customerId}")
    public Card fetchCardDetailsbyCutomerId(@PathVariable Integer customerId) {
        return cardService.getOneCustomerCardDetails(customerId);
    }

    /* use rest template /customers/{id} */
    @GetMapping(value = "/template/totalcustomers/{id}")
    public ResponseEntity<Customer> getCardDetails(@PathVariable int id){
        return cardService.getCustomerDetails(id);
    }

    /*Using Feign client */
    @GetMapping(value = "/template/totalcustomers-feign/{id}")
    public Customer getCardDetailsFeign(@PathVariable int id){
        return customerServiceProxy.getOneCustomer(id);
    }

    @PostMapping(path = "/cards")
    public ResponseEntity<Object> createCardData(@Valid @RequestBody Card card){
        cardService.saveCardDetails(card);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(card.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

   @PutMapping(path = "/cards")
   public void updateCardData(@Valid @RequestBody Card card){
       cardService.updateCardDetails(card);
   }


    @DeleteMapping(path = "/cards/{id}")
    public void deleteCardData(@PathVariable Integer id){
        cardService.deleteCardDetails(id);
    }

}
