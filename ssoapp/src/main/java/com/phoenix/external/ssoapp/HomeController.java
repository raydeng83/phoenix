package com.phoenix.external.ssoapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "You are accessing external app resource with SSO";
    }
}
