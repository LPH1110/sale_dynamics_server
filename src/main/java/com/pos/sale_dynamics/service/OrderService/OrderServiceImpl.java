package com.pos.sale_dynamics.service.OrderService;

import com.pos.sale_dynamics.domain.Order;
import com.pos.sale_dynamics.dto.OrderDTO;
import com.pos.sale_dynamics.repository.OrderRepository;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public ResponseEntity<Order> findById(long orderId) {
        Optional<Order> orderRecord = orderRepository.findById(orderId);
        return orderRecord.map(order -> new ResponseEntity<>(order, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        return null;
    }
}
