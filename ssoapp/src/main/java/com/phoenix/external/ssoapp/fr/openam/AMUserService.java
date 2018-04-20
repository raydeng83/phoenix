package com.phoenix.external.ssoapp.fr.openam;

import com.phoenix.external.ssoapp.model.User;
import org.json.JSONObject;

public interface AMUserService {
    void createUser(User user);

    String authenticateUser(String usernamne, String password);

    JSONObject invalidateSession(String tokenId);

    boolean verifyToken(String accessToken);
}
