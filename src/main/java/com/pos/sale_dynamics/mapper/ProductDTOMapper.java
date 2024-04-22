package com.pos.sale_dynamics.mapper;

import com.pos.sale_dynamics.domain.Product;
import com.pos.sale_dynamics.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Override
    public ProductDTO apply(Product product) {
        return new ProductDTO(
            product.getDescription(),product.getName()
        );
    }

    public Product mapToProduct(ProductDTO productDTO) {
        return new Product(
                productDTO.description(),
                productDTO.name()
        );
    }
}
