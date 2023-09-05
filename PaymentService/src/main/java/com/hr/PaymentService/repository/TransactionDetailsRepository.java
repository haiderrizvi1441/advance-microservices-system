package com.hr.PaymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.PaymentService.entity.TransactionDetails;

@Repository
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {
    
    // CUSTOM METHODS
    // Get Order Details by orderId
    TransactionDetails findByOrderId(long orderId);

}
