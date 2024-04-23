package com.pos.sale_dynamics.repository;

import com.pos.sale_dynamics.domain.Product;
import com.pos.sale_dynamics.domain.Property;
import com.pos.sale_dynamics.domain.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThumbnailRepository extends JpaRepository<Thumbnail,Long> {
    List<Thumbnail> findAll();
}
