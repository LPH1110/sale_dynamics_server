package com.pos.sale_dynamics.domain;

import com.pos.sale_dynamics.dto.PropertyDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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

    @Column(unique = true)
    private String sku;
    @Column(unique = true)
    private String barcode;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Property> properties= new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Product_Thumbnail",
            joinColumns = {@JoinColumn(name="product_id")},
            inverseJoinColumns = {@JoinColumn(name = "thumbnail_id")}
    )
    private List<Thumbnail> thumbnails = new ArrayList<>();

    public Product(
            String name,
            String description,
            String provider,
            Category category,
            String baseUnit,
            int salePrice,
            int comparedPrice,
            String sku,
            String barcode
    ) {
        this.name = name;
        this.description = description;
        this.provider = provider;
        this.category = category;
        this.baseUnit = baseUnit;
        this.salePrice = salePrice;
        this.comparedPrice = comparedPrice;
        this.sku = sku;
        this.barcode = barcode;
    }
}

