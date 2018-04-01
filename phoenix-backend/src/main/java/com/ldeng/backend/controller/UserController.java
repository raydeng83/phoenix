package com.ldeng.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldeng.backend.fr.openam.AMUserService;
import com.ldeng.backend.model.Role;
import com.ldeng.backend.model.User;
import com.ldeng.backend.model.UserRole;
import com.ldeng.backend.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AMUserService amUserService;

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
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
    public String forgetPassword(@RequestBody HashMap<String, Object> mapper) throws NotFoundException {
        ObjectMapper om = new ObjectMapper();

        HashMap<String, String> input = (HashMap<String, String>) om.convertValue(mapper.get("input"), Object.class);
        String username = om.convertValue(input.get("queryFilter"), String.class).replace("uid eq ", "");

        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new NotFoundException("Username "+username+" not found");
        }

        return amUserService.forgetPassword(username);
    }
}

