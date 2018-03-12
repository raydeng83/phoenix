package com.ldeng.backend.controller;

import com.ldeng.backend.model.Role;
import com.ldeng.backend.model.User;
import com.ldeng.backend.model.UserRole;
import com.ldeng.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public User createUser (@RequestBody User user) {
        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        return userService.createUser(user, userRoles);
    }

    @RequestMapping("/{username}")
//    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }
}

