package com.marvinmitchell.quotetoorder.customer;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public List<CustomerDto> getAllCustomers(@RequestParam(required = false, name = "sort", defaultValue = "") String sortBy) {
        if (!Set.of("name", "email").contains(sortBy)){
            sortBy = "";
        }
        
        return customerService.getAllCustomers(sortBy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> GetCustomer(@PathVariable Long id) {
        var customer = customerService.getCustomerByID(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }

}
