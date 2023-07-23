package com.bosch.order.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineItemRequest {
    private String productId;
    private Integer quantity;
}
