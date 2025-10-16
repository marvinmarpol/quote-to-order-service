package com.marvinmitchell.quotetoorder.customer.entities;

import lombok.Data;

@Data
public class RegisterCustomerRequest {
    private String name;
    private String email;
    private String password;
}
