package com.marvinmitchell.quotetoorder.customer;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marvinmitchell.quotetoorder.customer.entities.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, UUID> {}
