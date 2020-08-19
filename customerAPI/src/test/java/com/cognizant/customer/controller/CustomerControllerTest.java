package com.cognizant.customer.controller;

import com.cognizant.customer.exception.CustomerNotFoundException;
import com.cognizant.customer.model.Customer;
import com.cognizant.customer.repository.CustomerRepository;
import com.cognizant.customer.service.CustomerService;
import com.cognizant.customer.service.CustomerServiceImpl;
import com.netflix.ribbon.proxy.annotation.Http;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;


@ExtendWith(SpringExtension.class)
public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerControllerMock;

    @Mock
    private CustomerServiceImpl customerServiceMock;

    @Mock
    private CustomerRepository customerRepositoryMock;

    private Customer customer = new Customer();

    public Customer createCustomerTestData(){
        Customer customer = new Customer();
        this.customer.setId(1);
        this.customer.setAddress("palamaner");
        this.customer.setAge(27);
        this.customer.setName("Vyshnavi");
        this.customer.setEmailAddress("vyshnavi@gmail.com");
        this.customer.setPhoneNumber("7358342846");
        return this.customer;
    }

    @Test
    public void testCreateCustomer()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(customerRepositoryMock.save(any(Customer.class))).thenReturn(customer);

        Customer cust = this.createCustomerTestData();
        ResponseEntity<Object> responseEntity = customerControllerMock.createCustomerData(cust);
        System.out.println(responseEntity);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }

    @Test
    public void shouldThrowValidationError(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Customer cust = this.createCustomerTestData();
        cust.setPhoneNumber("4567");
        System.out.println(cust);
        Mockito.when(customerControllerMock.createCustomerData(cust)).thenThrow(new CustomerNotFoundException("Phone number should be of 10 digits"));

        Assertions.assertThrows(CustomerNotFoundException.class, () -> {
            customerControllerMock.createCustomerData(cust);
        });
    }


}
