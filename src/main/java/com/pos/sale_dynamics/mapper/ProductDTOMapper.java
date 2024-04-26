package com.pos.sale_dynamics.mapper;

import com.pos.sale_dynamics.domain.*;
import com.pos.sale_dynamics.dto.ProductDTO;
import com.pos.sale_dynamics.dto.PropertyDTO;
import com.pos.sale_dynamics.repository.ProductRepository;
import com.pos.sale_dynamics.repository.PropertyRepository;
import com.pos.sale_dynamics.repository.TagRepository;
import com.pos.sale_dynamics.repository.ThumbnailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Service
public class ProductDTOMapper implements Function<Product, ProductDTO> {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ThumbnailRepository thumbnailRepository;
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private TagRepository tagRepository;

    @Override
    public ProductDTO apply(Product product) {
        List<String> thumbnails = product.getThumbnails().stream().map(Thumbnail::getUrl).toList();
        List<PropertyDTO> properties = product.getProperties().stream().map(Property -> {
            List<String> tags = Property.getTags().stream().map(Tag::getName).toList();
            return new PropertyDTO(Property.getName(), tags);
        }).toList();

        return new ProductDTO(
                product.getName(),
                product.getDescription(),
                product.getProvider(),
                product.getCategory().getName(),
                product.getBaseUnit(),
                product.getSku(),
                product.getBarcode(),
                thumbnails,
                properties,
                product.getSalePrice(),
                product.getComparedPrice()

        );
    }

    public Product mapToProduct(ProductDTO productDTO) {
        Product product = new Product(
                productDTO.name(),
                productDTO.description(),
                productDTO.provider(),
                new Category(productDTO.category()),
                productDTO.baseUnit(),
                productDTO.salePrice(),
                productDTO.comparedPrice(),
                productDTO.sku(),
                productDTO.barcode()
        );


        productDTO
                .thumbnails()
                .forEach(url -> {
                    product.getThumbnails().add(new Thumbnail(url));
                });

        productDTO
                .properties()
                .forEach(propertyDTO -> {
                    Property property = new Property(propertyDTO.name());;
                    propertyDTO
                            .tags()
                            .forEach(tag -> {
                                property.getTags().add(new Tag(tag));
                            });
                    product.getProperties().add(property);
                });
        return product;
    }
}
