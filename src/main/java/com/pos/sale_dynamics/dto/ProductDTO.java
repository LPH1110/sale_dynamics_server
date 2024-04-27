package com.pos.sale_dynamics.dto;


import com.pos.sale_dynamics.domain.Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ProductDTO(
         String name,
         String description,
         String provider,
         String category,
         String baseUnit,
         String sku,
         String barcode,
         List<String> thumbnails,
         List<PropertyDTO> properties,
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

    public List<String> thumbnails() {return thumbnails ;}

    public List<PropertyDTO> properties() {return properties ;}

    public String category() {
        return category;
    }
    public String baseUnit() {
        return baseUnit;
    }
    public String sku() {
        return sku;
    }
    public String barcode() {
        return barcode;
    }
    public int salePrice() {
        return salePrice;
    }
    public int comparedPrice() {
        return comparedPrice;
    }
}
