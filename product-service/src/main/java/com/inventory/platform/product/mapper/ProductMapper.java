package com.inventory.platform.product.mapper;

import com.inventory.platform.product.dto.ProductRequest;
import com.inventory.platform.product.dto.ProductResponse;
import com.inventory.platform.product.entity.Product;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {



        private ProductMapper() {
        }

        public static Product toEntity(ProductRequest request) {

            return Product.builder()
                    .productName(request.getProductName())
                    .description(request.getDescription())
                    .price(request.getPrice())
                    .quantity(request.getQuantity())
                    .category(request.getCategory())
                    .build();
        }
        public static List<ProductResponse> toResponse(List<Product> products) {

            List<ProductResponse> responses = new ArrayList<>();

            for (Product product : products) {
                responses.add(toResponse(product));
            }

            return responses;
        }

        public static ProductResponse toResponse(Product product) {

            return ProductResponse.builder()
                    .id(product.getId())
                    .productName(product.getProductName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .category(product.getCategory())
                    .createdAt(product.getCreatedAt())
                    .build();
        }

}
