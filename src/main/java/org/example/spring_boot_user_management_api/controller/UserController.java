package org.example.spring_boot_user_management_api.controller;

import jakarta.validation.Valid;
import org.example.spring_boot_user_management_api.dto.UserRequest;
import org.example.spring_boot_user_management_api.dto.UserResponse;
import org.example.spring_boot_user_management_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    //Create (POST)
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request) {

        UserResponse response = userService.createUser(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    //Read all (GET)
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

}
