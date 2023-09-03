package com.hr.PaymentService.service;

import com.hr.PaymentService.model.PaymentRequest;

public interface PaymentService {

    long doPayment(PaymentRequest paymentRequest);
    
}
