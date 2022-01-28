package com.sudocat.xoxaserver.controller;

import com.sudocat.xoxaserver.domain.JoinRequest;
import com.sudocat.xoxaserver.domain.Session;
import com.sudocat.xoxaserver.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SessionController {
    private final SessionService sessionService;

    private static final Logger logger = LoggerFactory.getLogger(SessionController.class);

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/xoxa/session/create")
    public String createSession(@RequestBody String userName) {
        logger.info(String.format("Received create session request from user: %s", userName));
        return sessionService.createSession(userName);
    }

    @PostMapping("/xoxa/session/join")
    public String joinSession(@RequestBody JoinRequest joinRequest) {
        logger.info(String.format("Received join session request for %s from user: %s",
            joinRequest.getSessionId(), joinRequest.getUserId()));
        
        return sessionService.joinSession(joinRequest);
    }
}
