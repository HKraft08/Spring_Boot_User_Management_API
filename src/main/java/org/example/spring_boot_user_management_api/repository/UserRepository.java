package org.example.spring_boot_user_management_api.repository;

import org.example.spring_boot_user_management_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, Long id);
}
