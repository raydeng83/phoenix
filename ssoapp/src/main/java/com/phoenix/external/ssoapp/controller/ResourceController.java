package com.phoenix.external.ssoapp.controller;


import com.phoenix.external.ssoapp.fr.openam.AMUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ResourceController {

    @Autowired
    private AMUserService amUserService;

    @RequestMapping("/user")
    public String getUser(){
        return "test user";
    }

    @RequestMapping("/oauthSso")
    public String oauthSso(HttpServletRequest request) throws Exception {
        String bearerToken = null;
        if(request.getHeader("AuthorizationToken")!=null) {
            bearerToken = request.getHeader("AuthorizationToken");
        }

        if(request.getHeader("AuthorizationToken")!=null) {
            bearerToken = request.getHeader("AuthorizationToken");
        }

        String accessToken = bearerToken.substring(7);

        boolean access = amUserService.verifyToken(accessToken);

        if(access) {
            return "{\"client\":\"client 1\", \"type\":\"test\", \"value\":\"resource 1, " +
                    "resource 2, resource3\"}";
        } else {
            throw new Exception();
        }
    }
}
