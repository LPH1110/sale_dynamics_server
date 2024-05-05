package com.pos.sale_dynamics.repository;

import com.pos.sale_dynamics.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    @Query("SELECT o FROM Order o WHERE o.id = ?1")
    Optional<Order> findById(String orderId);

 }
