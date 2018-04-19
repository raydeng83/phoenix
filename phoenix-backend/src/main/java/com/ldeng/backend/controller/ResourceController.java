package com.ldeng.backend.controller;

import com.ldeng.backend.fr.openam.AMUserService;
import com.ldeng.backend.service.SessionService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private AMUserService amUserService;

    @RequestMapping("/{resource}")
    public String testResource(HttpServletRequest request, @PathVariable String resource) throws UnsupportedEncodingException {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        String tokenId = sessionService.getTokenIdBySessionId(sessionId);
        JSONObject jo = amUserService.accessEvaluation(resource, tokenId);


        return jo.toString();
    }
}
