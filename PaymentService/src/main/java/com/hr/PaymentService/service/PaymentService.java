package com.hr.PaymentService.service;

import com.hr.PaymentService.model.PaymentRequest;
import com.hr.PaymentService.model.PaymentResponse;


public interface PaymentService {

    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(String orderId);

    
    
}
