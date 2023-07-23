package com.bosch.product.mapper;

import com.bosch.product.model.ProductModel;
import com.bosch.product.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(ProductModel productModel);

    List<ProductDto> toDtos(List<ProductModel> productModels);
}
