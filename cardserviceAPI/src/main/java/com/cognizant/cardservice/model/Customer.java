package com.cognizant.cardservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties
public class Customer implements Serializable {

    /*
        o	Name (20 Alphanumeric)
        o	Age (2 digit)
        o	Email address (basic email validation)
        o	Address (100 Alphanumeric)
        o	Phone Number (10 digit)
             */

    private Integer id;

    private String name;

    private int age;

    private String emailAddress;

    private String address;

    private String phoneNumber;

    private Customer() {
    }

    public Customer(Integer id, String name, Integer age, String emailAddress, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.emailAddress = emailAddress;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", emailAddress='" + emailAddress + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }


}
