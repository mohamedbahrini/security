package com.example.security.bootstrap;

import com.example.security.entity.Review;
import com.example.security.entity.Role;
import com.example.security.entity.RoleName;
import com.example.security.entity.User;
import com.example.security.repository.ReviewRepository;
import com.example.security.service.RoleService;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InitDB implements CommandLineRunner {
    private ReviewRepository reviewRepository;
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public InitDB(ReviewRepository reviewRepository, RoleService roleService, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<RoleName> roles = Arrays.asList(RoleName.ROLE_ADMIN, RoleName.ROLE_USER);
        createRoles(roles);
        createUser();
        createReviews();
    }

    private void createReviews() {
        reviewRepository.save(new Review(null, "4", "1"));
        reviewRepository.save(new Review(null, "3", "1"));
        reviewRepository.save(new Review(null, "5", "1"));
        reviewRepository.save(new Review(null, "4", "2"));
    }

    void createRoles(List<RoleName> roleNames) {
        roleNames.forEach(roleName -> roleService.saveRole(new Role(roleName)));
    }

    void createUser() {
        User user = new User();
        user.setUsername("mohamed");
        user.setFirstname("Mohamed");
        user.setLastname("BAHRINI");
        user.setEmail("email@gmail.com");
        user.setPassword("12345");
        user.setEnabled(true);
        user.setRoles(roleService.getAllRoles().stream().collect(Collectors.toSet()));

        userService.saveUser(user);
    }
}
