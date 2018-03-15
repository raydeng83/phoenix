package com.phoenix.external.ssoapp.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static com.phoenix.external.ssoapp.utility.SecurityConstants.HEADER_STRING;
import static com.phoenix.external.ssoapp.utility.SecurityConstants.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER_STRING);
        String token = bearerToken.replace(TOKEN_PREFIX, "");
        if (token != null) {
            // parse the token.
            try {
                Algorithm algorithm = Algorithm.HMAC256("password");
                JWTVerifier verifier = JWT.require(algorithm)
                        .build(); //Reusable verifier instance
                DecodedJWT jwt = verifier.verify(token);
                System.out.println(jwt.getSubject());
            } catch (UnsupportedEncodingException exception){
                //UTF-8 encoding not supported
            } catch (JWTVerificationException exception){
                //Invalid signature/claims
            }

            return null;
        }
        return null;
    }
}