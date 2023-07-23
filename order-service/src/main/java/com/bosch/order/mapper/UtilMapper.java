package com.bosch.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface UtilMapper {
    @Named(value = "formatLocalDateTime")
    default String formatLocalDateTime(LocalDateTime localDateTime) {
        final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return localDateTime.format(formatter);
    }
}
