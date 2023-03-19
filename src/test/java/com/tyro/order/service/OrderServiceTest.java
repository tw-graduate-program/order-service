package com.tyro.order.service;

import com.tyro.order.domain.OrderInfo;
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

    private OrderInfo orderInfo;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository);
        orderInfo = OrderInfo.builder()
                .contactId(String.valueOf(777777))
                .orderType(Integer.valueOf(77))
                .orderStatus(Integer.valueOf(7))
                .orderFee(BigDecimal.valueOf(77777))
                .deliveryFee(BigDecimal.valueOf(77)).build();
    }

    @Test
    void should_return_all_orders() {
        // given
        when(orderRepository.getAllOrders()).thenReturn(List.of(orderInfo));

        // when
        List<OrderInfo> orderInfos = orderService.getAllOrders();

        // then
        assertThat(orderInfos).isEqualTo(List.of(orderInfo));
        verify(orderRepository).getAllOrders();
    }

    @Test
    void should_return_order_by_id() {
        // given
        Long id = 77L;
        orderInfo.setId(id);
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(orderInfo));

        // when
        OrderInfo orderInfoById = orderService.getOrderById(id);

        // then
        assertThat(orderInfoById).isEqualTo(orderInfo);
        verify(orderRepository).findById(any(Long.class));
    }

    @Test
    void should_save_order() {
        // given
        OrderInfo mockOrderInfo = orderInfo;
        Long id = 77L;
        mockOrderInfo.setId(id);
        when(orderRepository.save(orderInfo)).thenReturn(mockOrderInfo);

        // when
        OrderInfo actualOrderInfo = orderService.saveOrder(orderInfo);

        // then
        assertThat(actualOrderInfo.getId()).isNotNull();
        verify(orderRepository).save(any(OrderInfo.class));
    }

    @Test
    void should_update_order_by_id() {
        // given
        Long id = 77L;
        OrderInfo mockOrderInfo = orderInfo;
        mockOrderInfo.setId(id);
        when(orderRepository.save(any(OrderInfo.class))).thenReturn(mockOrderInfo);

        // when
        OrderInfo actualOrderInfo = orderService.updateById(id, orderInfo);

        // then
        assertThat(actualOrderInfo).isEqualTo(mockOrderInfo);
        verify(orderRepository).save(any(OrderInfo.class));
    }

    @Test
    void should_logical_delete_order_by_id() {
        // given
        Long id = 7L;
        when(orderRepository.removeById(any(Long.class))).thenReturn(1);

        // when
        orderService.removeById(id);

        // then
        verify(orderRepository).removeById(any(Long.class));
    }

}