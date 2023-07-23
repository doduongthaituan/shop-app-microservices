package com.bosch.product.controller;

import com.bosch.product.dto.ProductDto;
import com.bosch.product.request.ProductRequest;
import com.bosch.product.response.ProductResponse;
import com.bosch.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        // todo: validate
        ProductResponse productResponse = productService.createProduct(productRequest);
        // todo: return
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<?> getProduct(@PathVariable("product-id") String productId) {
        // todo: validate
        // Call service
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProduct(productId));
    }

    @GetMapping("/inventory")
    // http://localhost:8080/api/v1/product/inventory?product-code=123&product-code=456
    public ResponseEntity<?> getAvailableProducts(@RequestParam(name = "product-code") List<String> productCodes) {
        ProductResponse productResponse = productService.getAvailableProducts(productCodes);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }
}
