package com.vh.tutorial.redis.redis_tutorial.model.product;

import com.vh.tutorial.redis.redis_tutorial.model.product.dto.ProductDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity(name = "product")
@Table(name = "product_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank
    @Size(min = 10, max = 50)
    private String name;
    @NotBlank
    @Size(min = 10, max = 200)
    private String description;
    private boolean isActive;
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.isActive = true;
    }

    public void update(ProductDto product) {
        if (product.name() != null) {
            this.name = product.name();
        }
        if (product.description() != null) {
            this.description = product.description();
        }

        if (product.price() != null) {
            this.price = product.price();
        }
    }

    public void softDelete() {
        this.isActive = false;
    }


}
