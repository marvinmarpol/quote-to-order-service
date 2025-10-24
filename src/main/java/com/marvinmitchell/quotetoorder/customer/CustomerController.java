package com.marvinmitchell.quotetoorder.customer;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.marvinmitchell.quotetoorder.customer.entities.CustomerDto;
import com.marvinmitchell.quotetoorder.customer.entities.RegisterCustomerRequest;
import com.marvinmitchell.quotetoorder.customer.entities.UpdateCustomerRequest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(
            @Valid
            @RequestBody RegisterCustomerRequest payload,
            UriComponentsBuilder uriBuilder) {
        var customerDto = customerService.createCustomer(payload);
        if (customerDto == null) {
            return ResponseEntity.internalServerError().build();
        }

        var uri = uriBuilder.path("/customers/{id}").buildAndExpand(customerDto.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @PathVariable(name = "id") UUID id,
            @RequestBody UpdateCustomerRequest request) {
        CustomerDto customerDto = customerService.updateCustomer(request, id);
        if (customerDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> deleteCustomer(
            @PathVariable(name = "id") UUID id) {
        UUID deletedId = customerService.deleteCustomer(id);
        if (deletedId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getAllCustomers(
            @RequestParam(required = false, name = "sort", defaultValue = "") String sortBy) {
        if (!Set.of("name", "email").contains(sortBy)) {
            sortBy = "";
        }

        return ResponseEntity.ok(customerService.getAllCustomers(sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> GetCustomer(@PathVariable UUID id) {
        var customer = customerService.getCustomerByID(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(customer);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException ex) {
        return ResponseEntity.badRequest().body(
                Map.of("message", ex.getLocalizedMessage()));
    }

}
