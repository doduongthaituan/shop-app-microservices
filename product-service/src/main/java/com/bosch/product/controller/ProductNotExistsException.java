package com.bosch.product.controller;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductNotExistsException extends RuntimeException {
    private String message;
}
