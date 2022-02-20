package com.sudocat.xoxaserver.service;

import com.sudocat.xoxaserver.domain.JoinRequest;
import com.sudocat.xoxaserver.domain.ChatSession;
import com.sudocat.xoxaserver.repository.SessionRepository;
import com.sudocat.xoxaserver.utils.RandomString;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private static final Logger logger = LoggerFactory.getLogger(SessionService.class);

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public ChatSession createSession(String userName) {
        String sessionId = new ObjectId().toString();
        String sessionToken = new RandomString().nextString();
        String sessionJoinToken = new RandomString(8, ThreadLocalRandom.current()).nextString();
        ChatSession newSession = new ChatSession(sessionId, sessionToken, Collections.singleton(userName), sessionJoinToken);

        sessionRepository.insert(newSession);
        logger.info(String.format("\nCreated session with: \n%s", newSession));
        return newSession;
    }

    public ChatSession joinSession(JoinRequest joinRequest) {
        String newUser = joinRequest.getUserId();
        Optional<ChatSession> onGoing = sessionRepository.findById(joinRequest.getSessionId());
        if (onGoing.isPresent() &&
            onGoing.get().getId().equals(joinRequest.getSessionId()) &&
            onGoing.get().getJoinToken().equals(joinRequest.getJoinToken())){
            ChatSession session = onGoing.get();
            if (!session.getUsers().contains(newUser)) {
                session.getUsers().add(newUser);
                sessionRepository.save(session);
            }
        } else {
            throw new RuntimeException("Session info is wrong");
        }
        return onGoing.get();
    }
}
