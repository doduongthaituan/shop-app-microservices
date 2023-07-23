package com.bosch.order.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_table")
public class OrderModel extends BaseModel {

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "order_code_column", nullable = false, unique = true)
    @Builder.Default
    private String orderCode = UUID.randomUUID().toString();

    @OneToMany(mappedBy = "orderModel", cascade = {CascadeType.ALL})
    private List<LineItemModel> lineItemModels;
}
