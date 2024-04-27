package com.pos.sale_dynamics.controller;

import com.pos.sale_dynamics.service.ProductService.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.pos.sale_dynamics.dto.ProductDTO;
import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;


    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping("/save")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        System.out.println(productDTO.toString());
        return productService.addProduct(productDTO);
    }

    @GetMapping("/detail")
    public ProductDTO getDetail(@RequestParam String barcode) {
        return productService.findByBarcode(barcode);
    }
}
