package com.vh.tutorial.redis.redis_tutorial.controller;

import com.vh.tutorial.redis.redis_tutorial.model.product.Product;
import com.vh.tutorial.redis.redis_tutorial.model.product.dto.ProductDto;
import com.vh.tutorial.redis.redis_tutorial.service.ProductService;
import com.vh.tutorial.redis.redis_tutorial.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {


    @MockBean
    private ProductService productService;


    @Mock
    private Product product;

    @Mock
    private ProductDto productDto;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn200CodeforGetAllProductsRequest() throws Exception {
        //arrange + act
        MockHttpServletResponse response = this.mockMvc.perform(get("/v1/product")).andReturn().getResponse();

        //assert
        assertEquals(200, response.getStatus());
    }

    @Test
    public void shouldReturn200CodeforGetByidProductsRequest() throws Exception {
        //arrange + act
        MockHttpServletResponse response = this.mockMvc.perform(get("/v1/product/1")).andReturn().getResponse();

        //assert
        assertEquals(200, response.getStatus());
    }


    @Test
    public void shouldReturn200CodeforCreateProduct() throws Exception {
        // Arrange
        String json = """
                {
                    "name": "Product 1",
                    "description": "Product 1 description",
                    "price": 10.0
                }
                """;


        when(productService.save(any(ProductDto.class))).thenReturn(this.product);

        // Act
        MockHttpServletResponse response = this.mockMvc.perform(post("/v1/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)).andReturn().getResponse();

        // Assert
        assertEquals(201, response.getStatus());
    }



    @Test
    public void shouldReturn200CodeforUpdateProduct() throws Exception {
        //arrange
        String json = """
                {
                    "name": "Product 1",
                    "description": "Product 1 description",
                    "price": 10.0
                }
                                
                """;
        given(this.productService.update("1", productDto)).willReturn(productDto);
        //act
        MockHttpServletResponse response = this.mockMvc.perform(put("/v1/product/1").content(json).contentType(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        //assert
        assertEquals(200, response.getStatus());
    }


    @Test
    public void shouldReturn204ForDeleteProduct() throws Exception {
        //arrange + act
        MockHttpServletResponse response = this.mockMvc.perform(delete("/v1/product/1")).andReturn().getResponse();

        //assert
        assertEquals(204, response.getStatus());
    }


}