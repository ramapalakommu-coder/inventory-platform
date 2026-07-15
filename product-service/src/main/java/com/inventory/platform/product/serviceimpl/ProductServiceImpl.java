package com.inventory.platform.product.serviceimpl;

import com.inventory.platform.product.dto.ProductRequest;
import com.inventory.platform.product.dto.ProductResponse;
import com.inventory.platform.product.entity.Product;
import com.inventory.platform.product.exception.DuplicateResourceException;
import com.inventory.platform.product.exception.ProductNotFoundException;
import com.inventory.platform.product.mapper.ProductMapper;
import com.inventory.platform.product.repository.ProductRepository;
import com.inventory.platform.product.service.ProductService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    @Override
    public ProductResponse createProduct(ProductRequest request) {


        if (productRepository.existsByProductName(request.getProductName())) {
           throw new DuplicateResourceException("Product already exists with name : " + request.getProductName());
        }

        Product product = ProductMapper.toEntity(request);

        Product savedProduct = productRepository.save(product);
        log.info("User created successfully with id : {}", savedProduct.getId());


        return ProductMapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Product not found with id: " + id
                ));
        return ProductMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return ProductMapper.toResponse(products);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id : " + id));
        product.setProductName(request.getProductName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(request.getCategory());
        Product updatedProduct=productRepository.save(product);
        return ProductMapper.toResponse(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
