package com.phoenix.external.ssoapp.service.impl;

import com.phoenix.external.ssoapp.fr.openam.AMUserService;
import com.phoenix.external.ssoapp.model.Session;
import com.phoenix.external.ssoapp.model.User;
import com.phoenix.external.ssoapp.model.UserRole;
import com.phoenix.external.ssoapp.repository.RoleRepository;
import com.phoenix.external.ssoapp.repository.SessionRepository;
import com.phoenix.external.ssoapp.repository.UserRepository;
import com.phoenix.external.ssoapp.service.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private AMUserService amUserService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        User localUser = userRepository.findByEmail(user.getEmail());

        if (localUser != null) {
            LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            localUser = userRepository.save(user);

            amUserService.createUser(user);
        }

        return localUser;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Session setUserSession (String username, String sessionId, String tokenId) {
        Session session = new Session();
        session.setJsessionId(sessionId);
        session.setTokenId(tokenId);

        User user = getUserByUsername(username);
        session.setUser(user);
        return sessionRepository.save(session);
    }

    @Override
    public JSONObject invalidateUserSession (String username, String sessionId) {
        Session session = sessionRepository.findByJsessionId(sessionId);
        String tokenId = session.getTokenId();
        SecurityContextHolder.clearContext();
        sessionRepository.delete(session);
        return amUserService.invalidateSession(tokenId);
    }

}
