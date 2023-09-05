package com.hr.PaymentService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hr.PaymentService.entity.TransactionDetails;
import com.hr.PaymentService.model.PaymentMode;
import com.hr.PaymentService.model.PaymentRequest;
import com.hr.PaymentService.model.PaymentResponse;
import com.hr.PaymentService.repository.TransactionDetailsRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private TransactionDetailsRepository transactionDetailsRepository;
    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording Payment Details:{}", paymentRequest);

        // Building the object
        TransactionDetails transactionDetails = TransactionDetails.builder()
                                                .paymentDate(Instant.now())
                                                .paymentStatus("SUCCESS")
                                                .orderId(paymentRequest.getOrderId())
                                                .referenceNumber(paymentRequest.getReferenceNumber())
                                                .amount(paymentRequest.getAmount())
                                                .build();

        // Save the object
        transactionDetailsRepository.save(transactionDetails);
        log.info("Transaction Completed with Id: {}", transactionDetails.getId());
        return transactionDetails.getId();
    }


    @Override
    public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
        log.info("Getting the Order details for the Order id:{}",orderId);
        
        // Getting the transactiondetails object
        TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(Long.valueOf(orderId));

        // Converting to Requested Format : PaymentResponse
        PaymentResponse paymentResponse = PaymentResponse.builder()
                                            .paymentId(transactionDetails.getId())
                                            .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode())) // string-> enum
                                            .paymentDate(transactionDetails.getPaymentDate())
                                            .orderId(transactionDetails.getOrderId())
                                            .status(transactionDetails.getPaymentStatus())
                                            .amount(transactionDetails.getAmount())
                                            .build();

        // Returning the required format object
        return paymentResponse;

    }
    
}
