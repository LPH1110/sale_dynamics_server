package com.pos.sale_dynamics.service.OrderService;

import com.pos.sale_dynamics.domain.Order;
import com.pos.sale_dynamics.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> findAll();

    ResponseEntity<Order> findById(long orderId);

    Order createOrder(OrderDTO orderDTO);
}
