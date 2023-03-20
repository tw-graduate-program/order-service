package com.demo.order.service;

import com.demo.order.domain.OrderInfo;
import com.demo.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderInfo> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public OrderInfo getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    public OrderInfo saveOrder(OrderInfo orderInfo) {
        return orderRepository.save(orderInfo);
    }

    public OrderInfo updateById(Long id, OrderInfo orderInfo) {
        orderInfo.setId(id);
        return orderRepository.save(orderInfo);
    }

    public void removeById(Long id) {
        orderRepository.removeById(id);
    }
}
