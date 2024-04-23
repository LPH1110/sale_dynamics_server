package com.pos.sale_dynamics.repository;

import com.pos.sale_dynamics.domain.Product;
import com.pos.sale_dynamics.domain.Property;
import com.pos.sale_dynamics.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag,Long> {
    List<Tag> findAll();
}
