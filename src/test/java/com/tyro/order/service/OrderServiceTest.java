package com.tyro.order.service;

import com.tyro.order.domain.Order;
import com.tyro.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository);
        order = Order.builder()
                .contactId(String.valueOf(777777))
                .orderType(Integer.valueOf(77))
                .orderStatus(Integer.valueOf(7))
                .orderFee(BigDecimal.valueOf(77777))
                .deliveryFee(BigDecimal.valueOf(77)).build();
    }

    @Test
    void should_return_all_orders() {
        // given
        when(orderRepository.findAll()).thenReturn(List.of(order));

        // when
        List<Order> orders = orderService.getAllOrders();

        // then
        assertThat(orders).isEqualTo(List.of(order));
        verify(orderRepository).findAll();
    }

    @Test
    void should_return_order_by_id() {
        // given
        String id = String.valueOf(77);
        order.setId(id);
        when(orderRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(order));

        // when
        Order orderById = orderService.getOrderById(id);

        // then
        assertThat(orderById).isEqualTo(order);
        verify(orderRepository).findById(any(String.class));
    }

}