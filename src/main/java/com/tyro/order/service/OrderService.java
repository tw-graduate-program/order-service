package com.tyro.order.service;

import com.tyro.order.domain.Order;
import com.tyro.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        return Order.builder()
                .contactId(String.valueOf(777777))
                .orderType(Integer.valueOf(77))
                .orderStatus(Integer.valueOf(7))
                .orderFee(BigDecimal.valueOf(77777))
                .deliveryFee(BigDecimal.valueOf(77))
                .deleted(Boolean.FALSE).build();
    }
}
