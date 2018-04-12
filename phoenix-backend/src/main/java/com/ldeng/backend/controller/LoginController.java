package com.ldeng.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ldeng.backend.config.CustomAuthenticationProvider;
import com.ldeng.backend.fr.openam.AMUserService;
import com.ldeng.backend.model.*;
import com.ldeng.backend.service.OtpRefService;
import com.ldeng.backend.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@RestController
public class LoginController {

    @Autowired
    private AMUserService amUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private OtpRefService otpRefService;

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

    @RequestMapping(value = "/otp", method = RequestMethod.POST)
    public void otpPost(@RequestBody HashMap<String, Object> mapper, HttpServletRequest request){
        ObjectMapper om = new ObjectMapper();
        String otpId = (String) mapper.get("otpId");
        String passcode = (String) mapper.get("passcode");


        String token = amUserService.sendOtp(otpId, passcode);

        OtpRef otpRef = otpRefService.findById(Long.parseLong(otpId));
        User user = otpRef.getUser();

        HttpSession httpSession = request.getSession();
        Set<GrantedAuthority> authorities = new HashSet<>();
        Set<UserRole> userRoles = user.getUserRoles();
        userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
        Authentication authentication =  new UsernamePasswordAuthenticationToken(user, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String sessionId = httpSession.getId();
        Session session = userService.setUserSession(user.getUsername(), sessionId, token);
    }
}
