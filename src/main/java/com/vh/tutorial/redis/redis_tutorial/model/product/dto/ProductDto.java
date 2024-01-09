package com.vh.tutorial.redis.redis_tutorial.model.product.dto;

import com.vh.tutorial.redis.redis_tutorial.model.product.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductDto(
        @NotBlank
        @Size(min = 10, max = 50)
        String name,
        @NotBlank
        @Size(min = 10, max = 200)
        String description,
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal price
) {

    public ProductDto(Product product) {
        this(product.getName(), product.getDescription(), product.getPrice());
    }

}
