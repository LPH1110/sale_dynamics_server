package com.pos.sale_dynamics.service.OrderService;

import com.pos.sale_dynamics.domain.Order;
import com.pos.sale_dynamics.dto.OrderDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    ResponseEntity<String> confirmOrder(String orderId);

    List<OrderDTO> findAll();

    ResponseEntity<OrderDTO> findById(String orderId);

    ResponseEntity<OrderDTO> createOrder(OrderDTO orderDTO);

    ResponseEntity<OrderDTO> payOrder(String orderId, int received, int excess, int customerOwed);
}
