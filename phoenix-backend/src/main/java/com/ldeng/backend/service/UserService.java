package com.ldeng.backend.service;

import com.ldeng.backend.model.Session;
import com.ldeng.backend.model.User;
import com.ldeng.backend.model.UserRole;
import org.json.JSONObject;

import java.util.Set;

public interface UserService {
    User createUser(User user, Set<UserRole> userRoles, String accountType);

    User getUserByUsername(String username);

    Session setUserSession (String username, String sessionId, String tokenId);

    JSONObject invalidateUserSession (String username, String sessionId);

    User getUserByEmail(String email);

    User getUserByEmailAndAccountType(String email, String accountType);
}
