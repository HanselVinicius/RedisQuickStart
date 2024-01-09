package com.vh.tutorial.redis.redis_tutorial.controller;

import com.vh.tutorial.redis.redis_tutorial.model.product.Product;
import com.vh.tutorial.redis.redis_tutorial.model.product.dto.ProductDto;
import com.vh.tutorial.redis.redis_tutorial.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/product")
public class ProductController {


    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Pega todos os produtos paginado")
    public ResponseEntity getAllProducts(@PageableDefault(page = 0, size = 10)Pageable pageable) {
       return ResponseEntity.ok(this.productService.getAllPaginated(pageable));
    }

    @PostMapping
    @Operation(summary = "Cria um novo produto")
    public ResponseEntity createProduct(@RequestBody @Valid ProductDto productDto, UriComponentsBuilder uriComponentsBuilder) {
        Product save = this.productService.save(productDto);
        URI endereco = uriComponentsBuilder.path("/v1/product/{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(endereco).body(save);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pega um produto pelo id")
    public ResponseEntity getProductById(@PathVariable String id) {
        return ResponseEntity.ok(this.productService.getOneById(id));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um produto")
    public ResponseEntity updateProduct(@PathVariable String id,@RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.ok(this.productService.update(id,productDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um produto")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
