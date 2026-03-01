package com.ecommerce.monolith.customer.mapper;

import com.ecommerce.monolith.customer.dto.CustomerDTO;
import com.ecommerce.monolith.customer.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "fullName", expression = "java(customer.getFirstName() + ' ' + customer.getLastName())")
    CustomerDTO toDTO(Customer customer);

    Customer toEntity(CustomerDTO dto);
}