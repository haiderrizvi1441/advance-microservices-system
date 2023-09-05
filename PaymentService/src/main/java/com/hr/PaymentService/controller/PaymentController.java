package com.hr.PaymentService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.PaymentService.model.PaymentRequest;
import com.hr.PaymentService.model.PaymentResponse;
import com.hr.PaymentService.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    // Injection Service
    @Autowired
    private PaymentService paymentService;

    // Do the payment 
    @PostMapping
    public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest){
        return new ResponseEntity<>(paymentService.doPayment(paymentRequest), HttpStatus.OK);
    }

    // Get the Payment Details 
    @GetMapping("/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentDetailsbyOrderId(@PathVariable String orderId){
        return new ResponseEntity<>(
            paymentService.getPaymentDetailsByOrderId(orderId),
            HttpStatus.OK
        );
    }


    @GetMapping("/test")
    public String testing(){
        return "Payment API is working fine";
    }
}
