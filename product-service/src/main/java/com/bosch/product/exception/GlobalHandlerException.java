package com.bosch.product.exception;

import com.bosch.product.controller.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ProductNotExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleProductNotExistsException(ProductNotExistsException productNotExistsException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(productNotExistsException.getMessage());
    }
}
