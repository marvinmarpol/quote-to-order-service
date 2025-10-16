package com.marvinmitchell.quotetoorder.customer.entities;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    CustomerDto toDto(CustomerModel customerModel);
    CustomerModel toEntityModel(RegisterCustomerRequest request);
}
