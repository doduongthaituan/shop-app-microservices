package com.bosch.order.mapper;

import com.bosch.order.dto.LineItemDto;
import com.bosch.order.model.LineItemModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UtilMapper.class})
public interface LineItemMapper {

    @Mappings(value = {
            @Mapping(target = "createdAt", source = "createdAt", qualifiedByName = "formatLocalDateTime"),
            @Mapping(target = "lastModifiedAt", source = "lastModifiedAt", qualifiedByName = "formatLocalDateTime")
    })
    LineItemDto toDto(LineItemModel lineItemModel);

    List<LineItemDto> toDtos(List<LineItemModel> lineItemModels);
}
