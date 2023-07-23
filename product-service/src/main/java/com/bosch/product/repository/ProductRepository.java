package com.bosch.product.repository;

import com.bosch.product.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, String> {
    List<ProductModel> findByProductCodeIn(List<String> productCodes);
}
