package com.marvinmitchell.quotetoorder.customer.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterCustomerRequest {
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;
    
    @NotNull(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 8, max = 64, message = "Password must be between 8 to 64 characters long.")
    private String password;
}
