package org.example.spring_boot_user_management_api.exception;

public class ResourceNotFoundException extends RuntimeException {

    ResourceNotFoundException(String message) {
        super(message);
    }
}
