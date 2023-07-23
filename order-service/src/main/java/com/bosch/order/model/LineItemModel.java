package com.bosch.order.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "line_item_table")
public class LineItemModel extends BaseModel {

    @Column(name = "line_item_code_column", nullable = false, unique = true)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Builder.Default
    private String lineItemCode = UUID.randomUUID().toString();

    @Column(name = "product_id_column", nullable = false)
    private String productId;

    @Column(name = "quantity_column", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn
    private OrderModel orderModel;
}
