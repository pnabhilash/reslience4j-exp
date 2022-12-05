package com.abhi.tech.stack.circuitbreaker.ServiceB.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validate/card")
public class PaymentServiceController {

    @GetMapping
    public String verifyAndAcceptPayment(){
        return " Payment Service invoked by Client Service - Order ";
    }
  }
