package com.ldeng.backend.service.impl;

import com.ldeng.backend.repository.SessionRepository;
import com.ldeng.backend.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService{

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public String getTokenIdBySessionId(String sessionId) {
        return sessionRepository.findByJsessionId(sessionId).getTokenId();
    }
}
