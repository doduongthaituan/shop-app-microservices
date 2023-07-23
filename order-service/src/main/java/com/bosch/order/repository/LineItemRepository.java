package com.bosch.order.repository;

import com.bosch.order.model.LineItemModel;
import com.bosch.order.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemRepository extends JpaRepository<LineItemModel, String> {
    List<LineItemModel> findByOrderModel(OrderModel orderModel);
}
