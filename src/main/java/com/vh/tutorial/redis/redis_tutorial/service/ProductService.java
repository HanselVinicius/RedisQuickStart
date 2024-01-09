package com.vh.tutorial.redis.redis_tutorial.service;


import com.vh.tutorial.redis.redis_tutorial.model.product.Product;
import com.vh.tutorial.redis.redis_tutorial.model.product.dto.ListagemProductDto;
import com.vh.tutorial.redis.redis_tutorial.model.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Page<ListagemProductDto> getAllPaginated(Pageable pageable);

    Product getOneById(String id);

    Product save(ProductDto product);

    ProductDto update(String id, ProductDto product);

    void delete(String id);


}
