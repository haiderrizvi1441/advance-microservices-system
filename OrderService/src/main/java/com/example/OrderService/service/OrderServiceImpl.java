package com.example.OrderService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.OrderService.entity.Order;
import com.example.OrderService.model.OrderRequest;
import com.example.OrderService.model.OrderResponse;
import com.example.OrderService.repository.OrderRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("Placing Order.....");
        
        // Order will first be sent in form of Order Request, create new order object and set properties from orderRequest
        Order order = Order.builder()
                        .amount(orderRequest.getTotalAmount())
                        .orderStatus("CREATED")
                        .productId(orderRequest.getProductId())
                        .orderDate(Instant.now())
                        .quantity(orderRequest.getQuantity())
                        .build();

        order = orderRepository.save(order);
        log.info("Order Placed sucessfully with id: ",order.getId());
                        
        return order.getId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("Get Order details for OrderId: {}", orderId);
        Order order = orderRepository.findById(orderId).orElse(null);

        OrderResponse orderResponse = OrderResponse.builder()
                                    .orderId(order.getId())
                                    .orderStatus(order.getOrderStatus())
                                    .amount(order.getAmount())
                                    .orderDate(order.getOrderDate())
                                    .build();

        return orderResponse;
    }
    

    

}
