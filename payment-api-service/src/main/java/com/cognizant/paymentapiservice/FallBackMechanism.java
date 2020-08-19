package com.cognizant.paymentapiservice;

import com.cognizant.paymentapiservice.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.retry.Retry;

import java.util.function.Function;

@Component
public class FallBackMechanism  {


    @Autowired
    RestTemplate restTemplate;

    String cardServiceUrl = "/template/payments-feign/{id}";


    public void setRequestFactory(HttpComponentsClientHttpRequestFactory clientHttpRequestFactory) {
        restTemplate.setRequestFactory(clientHttpRequestFactory);
    }

/*

    public <T> T postForObject(String cardServiceUrl, HttpEntity<String> entity,
                               Class<T> class1)
    {
        CircuitBreaker circuitBreaker = SpringContextBridgedServiceImpl.services().getCircuitBreaker();
        Retry retry = SpringContextBridgedServiceImpl.services().getRetry();
        Function<String,T> decorateCircuitBreaker = CircuitBreaker
                .decorateFunction(circuitBreaker, arg0 -> {

                    return restTemplate.postForObject(cardServiceUrl, entity, Card.class);

                });
      //  log.info("Circuit Breaker state for " +circuitBreaker.getName() +" : "+ circuitBreaker.getState());
        return decorateRetry(decorateCircuitBreaker,cardServiceUrl,circuitBreaker.getState(),retry);
*/



}
