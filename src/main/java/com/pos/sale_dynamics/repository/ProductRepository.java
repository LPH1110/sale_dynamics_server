package com.pos.sale_dynamics.repository;

import com.pos.sale_dynamics.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product,String> {
    List<Product> findAll();

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:infix% OR p.barcode LIKE %:infix% OR p.sku LIKE %:infix% ORDER BY id LIMIT 50")
    List<Product> findByNameContaining(@Param("infix") String infix);

    Optional<Product> findByBarcode(String barcode);
}
