package com.learning.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.time.Duration;
import com.learning.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepository {

	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public List<Product> getAllProducts() {
		return IntStream.rangeClosed(1, 5).peek(ProductRepository::sleepExecution)
				.peek(i -> System.out.println("processing count : " + i)).mapToObj(e -> new Product(e, "Customer" + e))
				.collect(Collectors.toList());
	}

	public Flux<Product> getAllProductsStream() {
		return Flux.range(1, 5).delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("processing count in stream flow : " + i)).log()
				.map(i -> new Product(i, "customer" + i));
	}

	public Mono<Product> getProductByID(int id) {
		Mono<Product> productMono = getProductsList().filter(c -> c.getId() == id).next();
		return productMono;
	}
	
	public Flux<Product> getProductsList() {
		return Flux.range(1, 50).doOnNext(i -> System.out.println("processing count in stream flow : " + i))
				.map(i -> new Product(i, "customer" + i));
	}
}
