package com.marvinmitchell.quotetoorder.customer.entities;

import lombok.Data;

@Data
public class UpdateCustomerRequest {
    private String name;
    private String email;
}
