package com.demo.order.controller;

import com.demo.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.order.domain.OrderInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;


@WebMvcTest(OrderController.class)
@ExtendWith(value = SpringExtension.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private OrderService orderService;

    private OrderInfo orderInfo;

    @BeforeEach
    void setUp() {
        orderInfo = OrderInfo.builder()
                .contactId(String.valueOf(777777))
                .orderType(77)
                .orderStatus(7)
                .orderFee(BigDecimal.valueOf(77777))
                .deliveryFee(BigDecimal.valueOf(77))
                .deleted(Boolean.FALSE).build();
    }

    @Test
    void should_return_all_orders() throws Exception {
        // given
        when(orderService.getAllOrders()).thenReturn(List.of(orderInfo));

        // when
        MvcResult mvcResult = mockMvc.perform(get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        // then
        assertThat(response).isEqualTo(objectMapper.writeValueAsString(List.of(orderInfo)));
        verify(orderService).getAllOrders();
    }

    @Test
    void should_return_order_by_id() throws Exception {
        // given
        Long id = 77L;
        orderInfo.setId(id);
        when(orderService.getOrderById(any(Long.class))).thenReturn(orderInfo);

        // when
        mockMvc.perform(get("/orders/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contactId").value(orderInfo.getContactId()))
                .andExpect(jsonPath("$.orderType").value(orderInfo.getOrderType()))
                .andExpect(jsonPath("$.orderStatus").value(orderInfo.getOrderStatus()))
                .andExpect(jsonPath("$.orderFee").value(orderInfo.getOrderFee()))
                .andExpect(jsonPath("$.deliveryFee").value(orderInfo.getDeliveryFee()))
                .andReturn();

        // then
        verify(orderService).getOrderById(any());
    }

    @Test
    void should_save_order() throws Exception {
        // given
        Long id = 77L;
        orderInfo.setId(id);
        when(orderService.saveOrder(any(OrderInfo.class))).thenReturn(orderInfo);

        // when
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderInfo)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.contactId").value(orderInfo.getContactId()))
                .andExpect(jsonPath("$.orderType").value(orderInfo.getOrderType()))
                .andExpect(jsonPath("$.orderStatus").value(orderInfo.getOrderStatus()))
                .andExpect(jsonPath("$.orderFee").value(orderInfo.getOrderFee()))
                .andExpect(jsonPath("$.deliveryFee").value(orderInfo.getDeliveryFee()))
                .andReturn();

        // then
        verify(orderService).saveOrder(any(OrderInfo.class));
    }

    @Test
    void should_return_400_when_input_invalid() throws Exception {
        // given
        OrderInfo order = OrderInfo.builder()
                .orderType(77)
                .orderStatus(7)
                .orderFee(BigDecimal.valueOf(77777))
                .deliveryFee(BigDecimal.valueOf(77)).build();

        // when
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid Argument"))
                .andExpect(jsonPath("$.details.contactId").value("must not be null"))
                .andReturn();

        // then
    }

    @Test
    void should_update_order_by_id() throws Exception {
        // given
        Long id = 77L;
        OrderInfo mockOrder = orderInfo;
        mockOrder.setId(id);
        when(orderService.updateById(id, orderInfo)).thenReturn(mockOrder);

        // when
        MvcResult mvcResult = mockMvc.perform(post("/orders/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderInfo)))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        // then
        assertThat(response).isEqualTo(objectMapper.writeValueAsString(mockOrder));
        verify(orderService).updateById(any(Long.class), any(OrderInfo.class));
    }

    @Test
    void should_delete_order_by_id() throws Exception {
        // given
        Long id = 7L;

        // when
        mockMvc.perform(post("/orders/{id}/deletion", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // then
        verify(orderService).removeById(any(Long.class));
    }

    @AfterEach
    void tearDown() {
    }
}