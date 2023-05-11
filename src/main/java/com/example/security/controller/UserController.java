package com.example.security.controller;

import com.example.security.dto.ApiResponse;
import com.example.security.dto.SignUpRequest;
import com.example.security.entity.Role;
import com.example.security.entity.RoleName;
import com.example.security.entity.User;
import com.example.security.exception.ResourceNotFoundException;
import com.example.security.service.RoleService;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername()) || userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username or Email already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        Role userRole = roleService.getRoleByName(RoleName.ROLE_USER).orElseThrow(() -> new ResourceNotFoundException("Role", "name", "ROLE_USER"));
        User user = new User(signUpRequest.getUsername(), signUpRequest.getFirstname(), signUpRequest.getLastname(), signUpRequest.getEmail(), signUpRequest.getPassword(), true, Collections.singleton(userRole));

        User result = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @GetMapping("/test")
    public String test() {
        return "success test";
    }
}
