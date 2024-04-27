package com.pos.sale_dynamics.mapper;

import com.pos.sale_dynamics.domain.*;
import com.pos.sale_dynamics.dto.ProductDTO;
import com.pos.sale_dynamics.dto.PropertyDTO;
import com.pos.sale_dynamics.repository.*;
import com.pos.sale_dynamics.service.CategoryService.CategoryServiceImpl;
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

    @Autowired
    private CategoryServiceImpl categoryService;


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
        Category category = categoryService.findByName(productDTO.category()).get();

        Product product = productRepository.save(new Product(
                productDTO.name(),
                productDTO.description(),
                productDTO.provider(),
                category,
                productDTO.baseUnit(),
                productDTO.salePrice(),
                productDTO.comparedPrice(),
                productDTO.sku(),
                productDTO.barcode()
        ));

        //thumbnails
        productDTO.thumbnails().forEach(url -> {
            Thumbnail thumbnail = thumbnailRepository.save(new Thumbnail(url));
            product.getThumbnails().add(thumbnail);
        });

        productDTO
                .properties()
                .forEach(propertyDTO -> {
                    Property property = propertyRepository.save(new Property(propertyDTO.name(), product));
                    propertyDTO.tags().forEach(tagName -> {
                        Tag newTag = tagRepository.save(new Tag(tagName, property));
                        property.getTags().add(newTag);
                    });
                    propertyRepository.save(property);
                    product.getProperties().add(property);
                });
        return product;
    }
}
