package com.vh.tutorial.redis.redis_tutorial.model.product.dto;

import com.vh.tutorial.redis.redis_tutorial.model.product.Product;

import java.io.Serializable;
import java.math.BigDecimal;

public record ListagemProductDto(
        String id,
        String name,
        String description,
        BigDecimal price
) implements Serializable {

    public ListagemProductDto(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

}
