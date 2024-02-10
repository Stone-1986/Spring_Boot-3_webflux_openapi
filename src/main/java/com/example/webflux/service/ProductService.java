package com.example.webflux.service;

import com.example.webflux.dto.ProductDto;
import com.example.webflux.entity.Product;
import com.example.webflux.exception.CustomException;
import com.example.webflux.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final static String NAME_MESSAGE = "product name already in use";

    public Mono<Product> save(ProductDto dto) {
        Mono<Boolean> existsName = productRepository.findByName(dto.getName()).hasElement();
        return existsName.flatMap(exists -> exists ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST, NAME_MESSAGE))
                : productRepository.save(Product.builder().name(dto.getName()).price(dto.getPrice()).build()));
    }
}
