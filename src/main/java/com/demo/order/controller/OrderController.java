package com.demo.order.controller;

import com.demo.order.domain.OrderInfo;
import com.demo.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderInfo> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderInfo getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderInfo saveOrder(@RequestBody @Valid OrderInfo orderInfo) {
        return orderService.saveOrder(orderInfo);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderInfo updateOrderById(@PathVariable Long id,
                                     @RequestBody OrderInfo orderInfo) {
        return orderService.updateById(id, orderInfo);
    }

    @PostMapping("/{id}/deletion")
    @ResponseStatus(HttpStatus.OK)
    public String removeOrderById(@PathVariable Long id) {
        orderService.removeById(id);
        return "Succeed!";
    }
}
