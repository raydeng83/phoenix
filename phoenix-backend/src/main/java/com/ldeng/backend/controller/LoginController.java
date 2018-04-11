package com.ldeng.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldeng.backend.fr.openam.AMUserService;
import com.ldeng.backend.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;

@RestController
public class LoginController {

    @Autowired
    private AMUserService amUserService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ResponseEntity<String> logout(
            @RequestParam("logout") String logout
    ){
        return new ResponseEntity<>("Logout success.", HttpStatus.OK);
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest request, Principal principal) {
        String username = principal.getName();
        String sessionId = request.getSession().getId();

        userService.invalidateUserSession(username, sessionId);

    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public void authenticate(
            @RequestBody HashMap<String, Object> mapper
    ){
        ObjectMapper om = new ObjectMapper();
        String username = (String) mapper.get("username");
        String password = (String) mapper.get("password");

        amUserService.authenticateUser(username, password);
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping("/checkSession")
    public String checkSession() {
        return "Session Active";
    }

    @RequestMapping("/otp")
    public String otp(@RequestParam("otpId") String otpId) {
        return "otpId:"+otpId;
    }
}
