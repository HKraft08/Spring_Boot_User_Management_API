package org.example.spring_boot_user_management_api.service;

import jakarta.transaction.Transactional;
import org.example.spring_boot_user_management_api.dto.UserRequest;
import org.example.spring_boot_user_management_api.dto.UserResponse;
import org.example.spring_boot_user_management_api.entity.User;
import org.example.spring_boot_user_management_api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Create (POST)
    @Transactional
    public UserResponse createUser(UserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("User with this email already exists");
        }

        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    private UserResponse mapToResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
