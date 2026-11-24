package org.example.spring_boot_user_management_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 50, message = "Name cannot exceed 50 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Size(max = 120, message = "Email cannot exceed 120 characters")
        @Email(message = "Enter a valid email address")
        String email
) {
}
