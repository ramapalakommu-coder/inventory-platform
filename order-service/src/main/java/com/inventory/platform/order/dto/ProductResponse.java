package com.inventory.platform.order.dto;


import com.inventory.platform.order.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;

    private String productName;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private Category category;

    private LocalDateTime createdAt;
}
