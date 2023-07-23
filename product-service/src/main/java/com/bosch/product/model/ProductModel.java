package com.bosch.product.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.rmi.server.UID;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_table")
public class ProductModel extends BaseModel {

    @Column(name = "product_code_column", nullable = false, unique = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Builder.Default
    private String productCode = UUID.randomUUID().toString();

    @Column(name = "name_column", unique = true, nullable = false, columnDefinition = "NVARCHAR(255)")
    private String name;

//    @Column(name = "is_active_column", nullable = false)
//    @Builder.Default
//    private boolean isActive = true;

    @Column(name = "available_column", nullable = false)
    @Builder.Default
    private boolean available = true;
}
