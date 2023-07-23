package com.bosch.order.mapper;

import com.bosch.order.dto.OrderDto;
import com.bosch.order.model.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UtilMapper.class})
public interface OrderMapper {
    @Mappings(value = {
            @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "formatLocalDateTime"),
            @Mapping(target = "lastModifiedAt", source = "lastModifiedAt", qualifiedByName = "formatLocalDateTime")
    })
    OrderDto toDto(OrderModel orderModel);

    List<OrderDto> toDtos(List<OrderModel> orderModels);
}
