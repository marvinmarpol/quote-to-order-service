package com.marvinmitchell.quotetoorder.customer;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.marvinmitchell.quotetoorder.customer.entities.CustomerDto;
import com.marvinmitchell.quotetoorder.customer.entities.CustomerMapper;
import com.marvinmitchell.quotetoorder.customer.entities.RegisterCustomerRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Qualifier("baseCustomer")
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerDto createCustomer(RegisterCustomerRequest request) {
        var customer = customerMapper.toEntityModel(request);
        customerRepository.save(customer);

        return customerMapper.toDto(customer);
    }

    public List<CustomerDto> getAllCustomers(String sortBy) {

        Sort sort = (sortBy == null || sortBy.isBlank())
                ? Sort.unsorted()
                : Sort.by(sortBy);

        return customerRepository.findAll(sort)
                .stream()
                // .map(customerModel -> customerMapper.toDto(customerModel)) // altenative
                .map(customerMapper::toDto)
                .toList();

    }

    public CustomerDto getCustomerByID(UUID id) {
        var customer = customerRepository.findById(id).orElse(null);
        return customerMapper.toDto(customer);
    }

}
