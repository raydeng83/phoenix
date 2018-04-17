package com.ldeng.backend.fr.openam;

import com.ldeng.backend.model.User;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public interface AMUserService {
    void createUser (User user);

    String authenticateUser (String usernamne, String password);

    JSONObject invalidateSession(String tokenId);

    String fetchOIDCToken(String tokenId);

    String forgetPassword (String username);

    String sendOtp(String otpId, String passcode);

    HashMap googleLogin();

    String googleLoginPost(HashMap map)  throws UnsupportedEncodingException;
}
