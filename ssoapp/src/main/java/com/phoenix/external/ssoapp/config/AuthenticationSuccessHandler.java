package com.phoenix.external.ssoapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private RequestCache requestCache = new HttpSessionRequestCache();
    private final ObjectMapper mapper;


    @Autowired
    AuthenticationSuccessHandler(MappingJackson2HttpMessageConverter messageConverter) {
        this.mapper = messageConverter.getObjectMapper();
    }

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();

        String username = (String) authentication.getPrincipal();
        String sessionId = httpSession.getId();
        String tokenId = CustomAuthenticationProvider.getTokenId();

    }

    @Override
    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
