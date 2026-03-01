package com.ecommerce.monolith.order.mapper;

import com.ecommerce.monolith.order.entity.Order;
import com.ecommerce.monolith.order.dto.OrderDTO;
import com.ecommerce.monolith.order.dto.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "products", ignore = true)
    OrderDTO toDTO(Order order);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "totalAmount", ignore = true)
    Order toEntity(OrderRequest request);
}