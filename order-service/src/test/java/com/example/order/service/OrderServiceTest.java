package com.example.order.service;

import com.example.order.dto.OrderLineRequest;
import com.example.order.dto.OrderRequest;
import com.example.order.model.Order;
import com.example.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {
    @Test
    void createOrderTotals() {
        OrderService service = new OrderService(new OrderRepository());
        OrderLineRequest line = new OrderLineRequest();
        line.setProductId(10L);
        line.setQuantity(2);
        line.setUnitPrice(5.0);
        OrderRequest req = new OrderRequest();
        req.setLines(Collections.singletonList(line));
        Order order = service.create(req);
        assertEquals("NEW", order.getStatus());
        assertEquals(10.0, order.getTotalAmount(), 0.0001);
    }
}