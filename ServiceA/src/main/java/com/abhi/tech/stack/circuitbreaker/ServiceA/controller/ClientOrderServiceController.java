package com.abhi.tech.stack.circuitbreaker.ServiceA.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/place/order")
@Slf4j
public class ClientOrderServiceController {


    private RestTemplate restTemplate;


    private static final String BASE_URL_SERVICE_B ="http://localhost:8081/";

    private static final String SERVICE_A_APP="clientOrderService";


    public ClientOrderServiceController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private static int retry_count=1;

    @GetMapping
   // @Retry(name=SERVICE_A_APP,fallbackMethod="clientOrderServiceRetryFallBack")
    @CircuitBreaker(name=SERVICE_A_APP,fallbackMethod = "clientOrderServiceCBFallBack")
    public String serviceA(){
        System.out.println("Retry Count == > {} "+ retry_count++);
        String url= BASE_URL_SERVICE_B +"/validate/card";
        return restTemplate.getForObject(url,String.class);

    }

    public String clientOrderServiceCBFallBack(Exception e){
        return "This is a fallback method response from Circuit Breaker Client-OrderService";
    }

    public String clientOrderServiceRetryFallBack(Exception e){
        return "This is a fallback method  response from Retry - Client-OrderService";
    }


}
