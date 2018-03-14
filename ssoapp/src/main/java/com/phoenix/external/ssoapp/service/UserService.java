package com.phoenix.external.ssoapp.service;

import com.phoenix.external.ssoapp.model.Session;
import com.phoenix.external.ssoapp.model.User;
import com.phoenix.external.ssoapp.model.UserRole;
import org.json.JSONObject;

import java.util.Set;

public interface UserService {
    User createUser(User user, Set<UserRole> userRoles);

    User getUserByUsername(String username);

    Session setUserSession(String username, String sessionId, String tokenId);

    JSONObject invalidateUserSession(String username, String sessionId);
}
