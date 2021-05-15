package com.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.model.Product;
import com.learning.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {
	
    @Autowired
    private ProductService service;


    @GetMapping
    public List<Product> getAllProducts() {
        return service.loadAllProducts();
    }
    
    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable int id) {
        return service.getProductByID(id);
    }
    

    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> getAllProductsStream() {
        return service.loadAllProductsStream();
    }

}
