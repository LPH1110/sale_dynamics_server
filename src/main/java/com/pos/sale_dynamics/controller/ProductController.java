package com.pos.sale_dynamics.controller;

import com.pos.sale_dynamics.service.ProductService.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;
import com.pos.sale_dynamics.dto.ProductDTO;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/save")
    public ProductDTO addProduct(ProductDTO productDTO) {
        return productService.addProduct(productDTO);
    }
}
