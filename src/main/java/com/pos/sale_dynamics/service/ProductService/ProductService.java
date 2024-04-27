package com.pos.sale_dynamics.service.ProductService;

import com.pos.sale_dynamics.domain.Product;
import com.pos.sale_dynamics.dto.ProductDTO;

import java.util.List;

    public interface ProductService {
        List<ProductDTO> findAll();
        ProductDTO addProduct(ProductDTO productDTO);

        ProductDTO findByBarcode(String barcode);
}
