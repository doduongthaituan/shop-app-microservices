package com.bosch.order.repository;

import com.bosch.order.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, String> {
    Optional<OrderModel> findByOrderCode(String orderCode);
}
