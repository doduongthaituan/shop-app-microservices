package com.bosch.order.controller;

import com.bosch.order.request.OrderRequest;
import com.bosch.order.response.OrderResponse;
import com.bosch.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createAccount(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @GetMapping
    ResponseEntity<?> getOrders() {
        OrderResponse orderResponse = orderService.getOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @GetMapping("/{order-code}")
    ResponseEntity<?> getLineItemsByOrderCode(@PathVariable("order-code") String orderCode) {
        // todo: Validate -> Skip
        OrderResponse orderResponse = orderService.getLineItemByOrderCode(orderCode);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }
}
