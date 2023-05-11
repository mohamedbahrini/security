package com.example.security.repository;

import com.example.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    Optional<User> findByUsername(String name);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
