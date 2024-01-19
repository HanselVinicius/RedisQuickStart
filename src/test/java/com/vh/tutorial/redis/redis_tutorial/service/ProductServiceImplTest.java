package com.vh.tutorial.redis.redis_tutorial.service;

import com.vh.tutorial.redis.redis_tutorial.model.product.Product;
import com.vh.tutorial.redis.redis_tutorial.model.product.ProductRepository;
import com.vh.tutorial.redis.redis_tutorial.model.product.dto.ListagemProductDto;
import com.vh.tutorial.redis.redis_tutorial.model.product.dto.ProductDto;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {


    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductDto productDto;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Product product;



    @Test
    public void shouldSaveAnProduct() {
        // Act
        this.productService.save(this.productDto);
        // assert
        then(this.productRepository).should().save(new Product(this.productDto.name(), this.productDto.description(), this.productDto.price()));
    }

    @Test
    public void shouldReturnProductsPaginated() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        Page<Product> mockPage = mock(Page.class);
        when(productRepository.findAllByisActiveTrue(any(Pageable.class))).thenReturn(mockPage);

        // Act
        productService.getAllPaginated(pageable);
        // Assert
        then(productRepository).should().findAllByisActiveTrue(pageable);
    }

    @Test
    public void shouldReturnOneProductById(){
        //Arrange
        when(this.productRepository.findById("1")).thenReturn(Optional.ofNullable(this.product));
        //Act
        productService.getOneById("1");
        //Assert
        then(this.productRepository).should().findById("1");
    }

    @Test
    public void shouldThrowErrorWhenEntityNotFoundInGetOneById(){
        //Act + Assert
        Assertions.assertThrows(EntityNotFoundException.class, () -> productService.getOneById("1"));
    }

    @Test
    public void shouldUpdateProductData(){
        //Arrange
        given(this.productRepository.getReferenceById("1")).willReturn(this.product);
        //Act
        this.productService.update("1", this.productDto);

        //assert
        then(this.productRepository).should().getReferenceById("1");
        then(this.product).should().update(this.productDto);
    }

    @Test
    public void shouldDeleteProduct(){
        //arrange
        given(this.productRepository.getReferenceById("1")).willReturn(this.product);
        //act
        this.productService.delete("1");
        //assert
        then(this.productRepository).should().getReferenceById("1");
        then(this.product).should().softDelete();
    }


}