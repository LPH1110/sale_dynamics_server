package com.pos.sale_dynamics.repository;

import com.pos.sale_dynamics.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product,String> {
        List<Product> findAll();

    Optional<Product> findByBarcode(String barcode);
}
