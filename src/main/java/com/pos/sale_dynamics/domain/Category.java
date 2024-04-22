package com.pos.sale_dynamics.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Entity
@Data
@Setter
@Table(name = "categories")
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "category_name")
    private String name;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "Product_Category",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> productList;
}
