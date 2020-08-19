package com.cognizant.customer.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Customer {

    /*
        o	Name (20 Alphanumeric)
        o	Age (2 digit)
        o	Email address (basic email validation)
        o	Address (100 Alphanumeric)
        o	Phone Number (10 digit)
             */
    @Id
    @GeneratedValue
    private Integer id;

    @Length(max = 20, message = "Name cannot exceed 20 characters")
    private String name;

    @Range(min = 1, max = 99, message = "Age cannot be greater than two digits" )
    private int age;

    @Pattern(regexp=".+@.+\\..+", message="Wrong email!")
    private String emailAddress;

    @Length.List({
            @Length(min = 5, message = "The field must be at least 5 characters"),
            @Length(max = 50, message = "The field must be less than 50 characters")
    })
    private String address;

    @Pattern(regexp = "[0-9]{10}", message = "Phone number should be of 10 digits")
    private String phoneNumber;

    public Customer() {
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
