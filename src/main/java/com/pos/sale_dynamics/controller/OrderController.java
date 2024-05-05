package com.pos.sale_dynamics.controller;

import com.pos.sale_dynamics.domain.Order;
import com.pos.sale_dynamics.dto.OrderDTO;
import com.pos.sale_dynamics.requests.PayOrderRequest;
import com.pos.sale_dynamics.service.OrderService.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;
    @GetMapping
    public List<OrderDTO> getOrders() {
        return orderService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        System.out.println(orderDTO.toString());
        return orderService.createOrder(orderDTO);
    }

    @GetMapping("/detail")
    public ResponseEntity<OrderDTO> getOrderById(@RequestParam String orderId) {
        return orderService.findById(orderId);
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmOrder(@RequestParam String orderId) {
        return orderService.confirmOrder(orderId);
    }

    @PostMapping("/payment")
    public ResponseEntity<OrderDTO> payOrder(@RequestBody PayOrderRequest request) {
        return orderService.payOrder(
                request.orderId(),
                request.received(),
                request.excess(),
                request.customerOwed()
        );
    }

}
