package com.ldeng.backend.service;

import com.ldeng.backend.model.User;
import com.ldeng.backend.model.UserRole;

import java.util.Set;

public interface UserService {
    User createUser(User user, Set<UserRole> userRoles);

    User getUserByUsername(String username);
}
