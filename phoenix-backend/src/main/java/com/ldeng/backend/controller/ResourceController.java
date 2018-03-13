package com.ldeng.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @RequestMapping("/resource")
    public String testResource() {
        return "Test resource accessed";
    }
}
