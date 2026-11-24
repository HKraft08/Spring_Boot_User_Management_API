package org.example.spring_boot_user_management_api.service;

import org.example.spring_boot_user_management_api.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.example.spring_boot_user_management_api.dto.UserRequest;
import org.example.spring_boot_user_management_api.dto.UserResponse;
import org.example.spring_boot_user_management_api.entity.User;
import org.example.spring_boot_user_management_api.exception.DuplicateResourceException;
import org.example.spring_boot_user_management_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
            throw new DuplicateResourceException("A user with this email already exists");
        }

        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email());

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    //Read all (GET)
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    //Update (PUT)
    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {

        User user = findUserByIdOrThrow(id);

        boolean emailUsedByAnotherPerson = userRepository.existsByEmailAndIdNot(
                request.email(),
                id
        );

        if (emailUsedByAnotherPerson) {
            throw new DuplicateResourceException("Another user is already using this email");
        }

        user.setName(request.name());
        user.setEmail(request.email());

        User updatedUser = userRepository.save(user);

        return mapToResponse(updatedUser);
    }

    //Delete (DELETE)
    @Transactional
    public void deleteUser(Long id) {

        User user = findUserByIdOrThrow(id);

        userRepository.delete(user);
    }

    private User findUserByIdOrThrow(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

    private UserResponse mapToResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
