package com.bosch.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String productCode;
    private String name;
    private boolean available ;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
}
