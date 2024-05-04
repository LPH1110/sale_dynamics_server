package com.pos.sale_dynamics.repository;

import com.pos.sale_dynamics.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Query("SELECT o FROM OrderItem o WHERE o.order.id = ?1")
    List<OrderItem> findByOrderId(Long orderId);
}
