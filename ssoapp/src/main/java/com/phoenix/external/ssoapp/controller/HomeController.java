package com.phoenix.external.ssoapp.controller;

import com.phoenix.external.ssoapp.fr.openam.AMUserService;
import com.phoenix.external.ssoapp.model.User;
import com.phoenix.external.ssoapp.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.plugin.javascript.JSClassLoader;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private AMUserService amUserService;

    @RequestMapping("/onSSO")
    public String index(Principal principal, Model model) throws LoginException {
        User user = userService.getUserByUsername(principal.getName());

        if(user == null) {
            SecurityContextHolder.clearContext();
            throw new LoginException();
        } else {
            model.addAttribute("user", user);
        }
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(){
        SecurityContextHolder.clearContext();
    }
}
