package com.pos.sale_dynamics.repository;

import com.pos.sale_dynamics.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAll();

    @Query("SELECT o FROM Order o WHERE o.id = ?1")
    Optional<Order> findById(String orderId);

    @Query("SELECT SUM(o.total) FROM Order o WHERE o.createdDate >= ?1 AND o.createdDate <= ?2")
    Long calculateRevenue(LocalDateTime  from, LocalDateTime to);

    @Query("SELECT COUNT(o.id) FROM Order o WHERE o.createdDate >= ?1 AND o.createdDate <= ?2")
    Long countOrders(LocalDateTime  from, LocalDateTime to);

}
