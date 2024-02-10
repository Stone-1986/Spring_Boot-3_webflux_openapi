package com.example.webflux.handler;

import com.example.webflux.dto.ProductDto;
import com.example.webflux.entity.Product;
import com.example.webflux.service.ProductService;
import com.example.webflux.validation.ObjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class ProductHandler {
    private final ProductService productService;
    private final ObjectValidator objectValidator;

    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<ProductDto> dtoMono = request.bodyToMono(ProductDto.class).doOnNext(objectValidator::validate);
        return dtoMono.flatMap(productDto ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(productService.save(productDto), Product.class));
    }
}
