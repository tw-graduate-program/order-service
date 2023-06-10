package com.demo.order.service;

import com.demo.order.domain.OrderInfo;
import com.demo.order.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderInfo> getAllOrders() {
        List<OrderInfo> orderInfos = orderRepository.getAllOrders().stream()
                .filter(orderInfo -> orderInfo.getOrderStatus().compareTo(Integer.valueOf(1)) == 0)
                .collect(Collectors.toList());
        return orderInfos;
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
