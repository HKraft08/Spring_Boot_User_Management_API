package org.example.spring_boot_user_management_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserResponse(

        Long id,
        String name,
        String email
) {
}
