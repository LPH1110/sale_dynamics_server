package com.pos.sale_dynamics.repository;

import com.pos.sale_dynamics.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAll();
}
