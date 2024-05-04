package com.pos.sale_dynamics.repository;

import com.pos.sale_dynamics.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();
 }
