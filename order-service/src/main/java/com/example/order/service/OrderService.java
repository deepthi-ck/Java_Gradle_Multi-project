package com.example.order.service;

import com.example.common.model.Money;
import com.example.order.dto.OrderLineRequest;
import com.example.order.dto.OrderRequest;
import com.example.order.model.Order;
import com.example.order.model.OrderLine;
import com.example.order.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id));
    }

    public Order create(OrderRequest request) {
        Order order = new Order();
        order.setStatus("NEW");
        List<OrderLine> lines = new ArrayList<>();
        Money total = new Money(0.0);
        for (OrderLineRequest lineReq : request.getLines()) {
            OrderLine line = new OrderLine();
            line.setProductId(lineReq.getProductId());
            line.setQuantity(lineReq.getQuantity());
            line.setUnitPrice(lineReq.getUnitPrice());
            lines.add(line);
            total = total.plus(new Money(lineReq.getUnitPrice()).times(lineReq.getQuantity()));
        }
        order.setLines(lines);
        order.setTotalAmount(total.getAmount().doubleValue());
        return repository.save(order);
    }
}