package org.example.spring_boot_user_management_api.exception;

public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }

}
