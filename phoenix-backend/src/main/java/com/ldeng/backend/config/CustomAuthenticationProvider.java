package com.ldeng.backend.config;

import com.ldeng.backend.fr.openam.AMUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider
        implements AuthenticationProvider {

    static private String tokenId = null;

    @Autowired
    private AMUserService amUserService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        tokenId = (amUserService.authenticateUser(username, password));
        if ( tokenId !=null) {
            return new UsernamePasswordAuthenticationToken(
                    username, password, new ArrayList<>());
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }

    public static String getTokenId (){
        return tokenId;
    }
}
