package com.bosch.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LineItemDto {

    private String lineItemCode;

    private String productId;

    private Integer quantity;

    private String createdAt;

    private String lastModifiedAt;

}
