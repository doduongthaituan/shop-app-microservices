package com.bosch.order.service.impl;

import com.bosch.order.dto.LineItemDto;
import com.bosch.order.dto.OrderDto;
import com.bosch.order.dto.ProductDto;
import com.bosch.order.mapper.LineItemMapper;
import com.bosch.order.mapper.OrderMapper;
import com.bosch.order.model.LineItemModel;
import com.bosch.order.model.OrderModel;
import com.bosch.order.repository.LineItemRepository;
import com.bosch.order.repository.OrderRepository;
import com.bosch.order.request.LineItemRequest;
import com.bosch.order.request.OrderRequest;
import com.bosch.order.response.OrderResponse;
import com.bosch.order.response.ProductResponse;
import com.bosch.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final LineItemRepository lineItemRepository;
    private final OrderMapper orderMapper;
    private final LineItemMapper lineItemMapper;
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;

    @Override
    public OrderResponse createAccount(OrderRequest orderRequest) {
        if(Objects.isNull(orderRequest)) throw new RuntimeException("Order is required");
        // todo: check out of stock
        List<String> productCodes = orderRequest.getLineItems().stream().map(LineItemRequest::getProductId).toList();
        ProductResponse productResponses = webClientBuilder.build().get().uri("http://product-service/api/v1/product/inventory",
                uriBuilder -> uriBuilder.queryParam("product-code", productCodes).build())
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
        ProductDto[] productDtos = objectMapper.convertValue(productResponses.getData(), ProductDto[].class);
        boolean productsIsInStock = Arrays.stream(productDtos).allMatch(ProductDto::isAvailable);
        if(!productsIsInStock) {
            return OrderResponse.builder()
                    .message("Some products out of stock")
                    .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .build();
        }
        OrderModel orderModel = OrderModel.builder()
                .build();
        orderRepository.save(orderModel);
        log.info("Order {} saved successfully", orderModel.getId());
        List<LineItemModel> lineItemModels = orderRequest.getLineItems().stream()
                .map(item -> LineItemModel.builder()
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .orderModel(orderModel)
                        .build())
                .collect(Collectors.toList());
        lineItemRepository.saveAll(lineItemModels);
        log.info("Line items saved successfully");
        OrderDto orderDto = orderMapper.toDto(orderModel);
        return OrderResponse.builder()
                .message("Order stored successfully")
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value())
                .data(orderDto).build();
    }

    @Override
    public OrderResponse getOrders() {
        List<OrderModel> orderModels = orderRepository.findAll();
        List<OrderDto> orderDtos = orderMapper.toDtos(orderModels);
        return OrderResponse.builder()
                .message("Get orders successfully")
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value())
                .data(orderDtos).build();
    }

    @Override
    public OrderResponse getLineItemByOrderCode(String orderCode) {
        OrderModel orderModel = orderRepository.findByOrderCode(orderCode).orElseThrow(() -> new RuntimeException("Order code not found"));

        List<LineItemModel> lineItemModels = orderModel.getLineItemModels().stream().toList();
        List<LineItemDto> lineItemDtos = lineItemMapper.toDtos(lineItemModels);
        return OrderResponse.builder()
                .message("Get line items successfully")
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value())
                .data(lineItemDtos).build();
    }
}
