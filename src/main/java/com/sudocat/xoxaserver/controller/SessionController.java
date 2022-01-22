package com.sudocat.xoxaserver.controller;

import com.sudocat.xoxaserver.domain.JoinRequest;
import com.sudocat.xoxaserver.domain.Session;
import com.sudocat.xoxaserver.service.SessionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/xoxa/session/create")
    public Session createSession(@RequestBody String displayName) {
       return sessionService.createSession(displayName);
    }

    @PostMapping("/xoxa/session/join")
    public Session joinSession(@RequestBody JoinRequest joinRequest) {
        return sessionService.joinSession(joinRequest);
    }
}
