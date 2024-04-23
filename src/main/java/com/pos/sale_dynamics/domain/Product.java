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
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    private String name;
    private String description;
    private String provider;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    private String baseUnit;
    private int salePrice;
    private int comparedPrice;
    private String sku;
    private String barcode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Property> properties;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Product_Thumbnail",
            joinColumns = {@JoinColumn(name="product_id")},
            inverseJoinColumns = {@JoinColumn(name = "thumbnail_id")}
    )
    private List<Thumbnail> thumbnails;

    public Product(String name, String description) {
        this.description = description;
        this.name = name;
    }
}

