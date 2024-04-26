package com.pos.sale_dynamics.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "property")
    private List<Tag> tags = new ArrayList<>();


    public Property() {
        this.name = "";
        this.product = null;
    }

    public Property(String name) {
        super();
        this.name = name;
    }

    public void setProduct(Product product) {
        if (product == null) {
            if (this.product != null) {
                this.product.getProperties().remove(this);
            }
        } else {
            product.getProperties().add(this);
        }
        this.product = product;
    }

}
