package com.pos.sale_dynamics.controller;

import com.pos.sale_dynamics.domain.Order;
import com.pos.sale_dynamics.dto.OrderDTO;
import com.pos.sale_dynamics.service.OrderService.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;
    @GetMapping
    public List<Order> getOrders() {
        return orderService.findAll();
    }

    @PostMapping("/create")
    public Order createOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }
}
