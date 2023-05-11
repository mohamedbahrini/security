package com.example.security.service;

import com.example.security.entity.Role;
import com.example.security.entity.RoleName;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    Role saveRole(Role role);

    List<Role> getAllRoles();

    Optional<Role> getRoleByName(RoleName roleUser);
}
