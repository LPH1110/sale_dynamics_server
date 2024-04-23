package com.pos.sale_dynamics.dto;


import com.pos.sale_dynamics.domain.Category;

public record ProductDTO(
         String name,
         String description,
         String provider,
         String category,
         String baseUnit,
         String sku,
         String barcode,
         int salePrice,
         int comparedPrice
) {
    public String description() {
        return description;
    }

    public String name() {
        return name;
    }

    public String provider() {
        return provider;
    }

    public String category() {
        return category;
    }
    public String baseUnit() {
        return baseUnit;
    }
    public String sku() {
        return baseUnit;
    }
    public String barcode() {
        return baseUnit;
    }
    public int salePrice() {
        return salePrice;
    }
    public int comparedPrice() {
        return comparedPrice;
    }
}
