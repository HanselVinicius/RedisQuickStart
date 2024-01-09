package com.vh.tutorial.redis.redis_tutorial.service;

import com.vh.tutorial.redis.redis_tutorial.model.product.Product;
import com.vh.tutorial.redis.redis_tutorial.model.product.ProductRepository;
import com.vh.tutorial.redis.redis_tutorial.model.product.dto.ListagemProductDto;
import com.vh.tutorial.redis.redis_tutorial.model.product.dto.ProductDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Cacheable(value = "product", key = "'allProducts'")
    public Page<ListagemProductDto> getAllPaginated(Pageable pageable) {
        return productRepository.findAllByisActiveTrue(pageable).map(ListagemProductDto::new);
    }

    @Override
    @Cacheable(value = "product", key = "#id")
    public Product getOneById(String id) {
        return this.productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    @CacheEvict(value = "product", allEntries = true)
    public Product save(ProductDto product) {
        return this.productRepository.save(new Product(product.name(), product.description(), product.price()));
    }

    @Override
    @Transactional
    @CacheEvict(value = "product", allEntries = true)
    public ProductDto update(String id, ProductDto product) {
        Product productReference = this.productRepository.getReferenceById(id);
        productReference.update(product);
        return new ProductDto(productReference);
    }

    @Override
    @Transactional
    @CacheEvict(value = "product", allEntries = true)
    public void delete(String id) {
        this.productRepository.getReferenceById(id).softDelete();
    }
}
