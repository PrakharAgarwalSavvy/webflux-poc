package com.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.model.Product;
import com.learning.repository.ProductRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;


    public List<Product> loadAllProducts() {
        long start = System.currentTimeMillis();
        List<Product> Products = repo.getAllProducts();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return Products;
    }
    
    public Mono<Product> getProductByID(int id){
    	return repo.getProductByID(id);
    }

    public Flux<Product> loadAllProductsStream() {
        long start = System.currentTimeMillis();
        Flux<Product> Products = repo.getAllProductsStream();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return Products;
    }
}