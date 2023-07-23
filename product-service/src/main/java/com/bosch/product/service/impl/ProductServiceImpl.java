package com.bosch.product.service.impl;

import com.bosch.product.controller.ProductNotExistsException;
import com.bosch.product.dto.ProductDto;
import com.bosch.product.mapper.ProductMapper;
import com.bosch.product.model.ProductModel;
import com.bosch.product.repository.ProductRepository;
import com.bosch.product.request.ProductRequest;
import com.bosch.product.response.ProductResponse;
import com.bosch.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        log.info("Product with name: {}", productRequest.getName());
        // todo: validate
        // todo: Make product
        ProductModel productModel = ProductModel.builder()
                .name(productRequest.getName())
                .available(productRequest.isAvailable())
                .build();
        // todo: save
        productRepository.save(productModel);
        ProductDto productDto = productMapper.toDto(productModel);
        return ProductResponse.builder()
                .message("Product stored successfully")
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value())
                .data(productDto)
                .build();
    }

    @Override
    public List<ProductDto> getProducts() {
        // todo: find all
        List<ProductModel> productModels = productRepository.findAll();
        // todo: Map to dto
        return productMapper.toDtos(productModels);
    }

    @Override
    public ProductDto getProduct(String productId) {
        ProductModel productModel = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotExistsException("Can not find product with productId = " + productId));
        return productMapper.toDto(productModel);
    }

    @Override
    public ProductResponse getAvailableProducts(List<String> productCodes) {
        List<ProductModel> productModels = productRepository.findByProductCodeIn(productCodes);
        List<ProductDto> productDtos = productMapper.toDtos(productModels);
        return ProductResponse.builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .message("getAvailableProducts")
                .data(productDtos)
                .build();
    }
}
