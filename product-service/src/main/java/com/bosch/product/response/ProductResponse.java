package com.bosch.product.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse extends BaseResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
