package com.bosch.product.service;


import com.bosch.product.dto.ProductDto;
import com.bosch.product.request.ProductRequest;
import com.bosch.product.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductDto> getProducts();

    ProductDto getProduct(String productId);

    ProductResponse getAvailableProducts(List<String> productCodes);
}
