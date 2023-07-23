package com.bosch.order.service;

import com.bosch.order.request.OrderRequest;
import com.bosch.order.response.OrderResponse;

public interface OrderService {
    OrderResponse createAccount(OrderRequest orderRequest);

    OrderResponse getOrders();

    OrderResponse getLineItemByOrderCode(String orderCode);
}
