package com.pos.sale_dynamics.service.ProductService;

import java.util.List;

import com.pos.sale_dynamics.domain.*;
import com.pos.sale_dynamics.dto.ProductDTO;
import com.pos.sale_dynamics.mapper.ProductDTOMapper;
import com.pos.sale_dynamics.repository.ProductRepository;
import com.pos.sale_dynamics.repository.PropertyRepository;
import com.pos.sale_dynamics.repository.TagRepository;
import com.pos.sale_dynamics.repository.ThumbnailRepository;
import com.pos.sale_dynamics.service.CloudinaryService.CloudinaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ThumbnailRepository thumbnailRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProductDTOMapper productMapper;

    @Autowired
    private CloudinaryServiceImpl cloudinaryService;

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(product -> productMapper.apply(product)).toList();
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = productMapper.mapToProduct(productDTO);
        return productMapper.apply(productRepository.save(product));
    }

    @Override
    public ProductDTO findByBarcode(String barcode) {
        Product product = productRepository.findByBarcode(barcode).get();
        return productMapper.apply(product);
    }
}
