package com.vh.tutorial.redis.redis_tutorial.model.product;

import io.lettuce.core.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findAllByisActiveTrue(Pageable pageable);
}
