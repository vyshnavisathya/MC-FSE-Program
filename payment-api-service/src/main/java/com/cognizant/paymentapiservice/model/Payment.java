package com.cognizant.paymentapiservice.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;


@Entity
@Data
public class Payment {
    /*
    o	Card Number (16 digit)
o	Card Type (Visa/Master Card/Amex)
o	Customer Id
o	Expiration date (MM/DD/YYYY)
o	Payment amount (5 digit)
o	Status (Open/Closed/Failure)

     */

    @Id
    @GeneratedValue
    private Integer id;

    private String cardNumber;

    private String cardType;

    private Integer customerId;

    private String expiryDate;

    @Digits(integer=5, fraction=0)
    //@Length(min = 5, message = "Enter 5 digits number")
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Payment() {
    }

    public Payment(Integer id, String cardNumber, String cardType, Integer customerId, String expiryDate, int amount, Status status) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.customerId = customerId;
        this.expiryDate = expiryDate;
        this.amount = amount;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
