package com.pos.sale_dynamics.service.ProductService;

import java.util.List;

import com.pos.sale_dynamics.domain.Product;
import com.pos.sale_dynamics.dto.ProductDTO;
import com.pos.sale_dynamics.mapper.ProductDTOMapper;
import com.pos.sale_dynamics.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductDTOMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(productMapper).collect(Collectors.toList());
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = productMapper.mapToProduct(productDTO);
        productRepository.save(product);
        return productDTO;
    }
}
