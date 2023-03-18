package com.tyro.order.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyro.order.domain.Order;
import com.tyro.order.service.OrderService;
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

    private Order order;

    @BeforeEach
    void setUp() {
        order = Order.builder()
                .contactId(String.valueOf(777777))
                .orderType(Integer.valueOf(77))
                .orderStatus(Integer.valueOf(7))
                .orderFee(BigDecimal.valueOf(77777))
                .deliveryFee(BigDecimal.valueOf(77)).build();
    }

    @Test
    void should_return_all_orders() throws Exception {
        // given
        when(orderService.getAllOrders()).thenReturn(List.of(order));

        // when
        MvcResult mvcResult = mockMvc.perform(get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        // then
        assertThat(response).isEqualTo(objectMapper.writeValueAsString(List.of(order)));
        verify(orderService).getAllOrders();
    }

    @AfterEach
    void tearDown() {
    }
}