package com.cognizant.cardservice.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Pattern;
import java.util.Date;


@Entity
@Data
public class Card {

    @Id
    @GeneratedValue
    private Integer id;

    //    @ManyToOne
    private Integer customerId;

    @Pattern(regexp = "[0-9]{4}[0-9]{4}[0-9]{4}[0-9]{4}", message="Enter a Valid credit card number")
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @FutureOrPresent(message = "Enter a Valid Expiry date")
    @DateTimeFormat
    private Date expiryDate;

    public Card() {
    }

    public Card(Integer id, int customerId, String cardNumber, CardType cardType, Date expiryDate) {
        this.id = id;
        this.customerId = customerId;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cardType = cardType;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {

        this.expiryDate = expiryDate;
    }

    public CardType getCardType() {

        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "Card{" +
                "customerId='" + customerId + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}
