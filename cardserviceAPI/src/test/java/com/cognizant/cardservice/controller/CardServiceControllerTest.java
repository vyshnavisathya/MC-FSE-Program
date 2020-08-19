package com.cognizant.cardservice.controller;


import com.cognizant.cardservice.dao.CardServiceDao;
import com.cognizant.cardservice.entity.Card;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CardServiceControllerTest {

    @InjectMocks
    private CardServiceController cardServiceControllerMock;

    @Mock
    private CardServiceDao cardServiceDaoMock;

    /*@Test
    public void testCreateCustomer()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(cardServiceDaoMock.save(any(Card.class))).thenReturn(customer);

        Customer cust = this.createCustomerTestData();
        ResponseEntity<Object> responseEntity = customerControllerMock.createCustomerData(cust);
        System.out.println(responseEntity);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }*/
}
