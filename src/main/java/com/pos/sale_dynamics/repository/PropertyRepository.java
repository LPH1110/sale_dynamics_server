package com.pos.sale_dynamics.repository;

import com.pos.sale_dynamics.domain.Product;
import com.pos.sale_dynamics.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Long> {
    List<Property> findAll();

}
