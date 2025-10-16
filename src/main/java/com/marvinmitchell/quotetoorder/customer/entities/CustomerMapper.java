package com.marvinmitchell.quotetoorder.customer.entities;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    CustomerDto toDto(CustomerModel customerModel);
    CustomerModel toEntityModel(RegisterCustomerRequest request);
    void update(UpdateCustomerRequest request, @MappingTarget CustomerModel customerModel);
}
