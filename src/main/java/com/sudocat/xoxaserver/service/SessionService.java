package com.sudocat.xoxaserver.service;

import com.sudocat.xoxaserver.domain.JoinRequest;
import com.sudocat.xoxaserver.domain.Session;
import com.sudocat.xoxaserver.repository.SessionRepository;
import com.sudocat.xoxaserver.utils.RandomString;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public Session createSession(String displayName) {
        String sessionId = new ObjectId().toString();
        String sessionToken = new RandomString().nextString();
        String newUser = new ObjectId() + "-" + displayName;
        String sessionJoinToken = new RandomString(8, ThreadLocalRandom.current()).nextString();
        Session newSession = new Session(sessionId, sessionToken, Collections.singleton(newUser), sessionJoinToken);

        sessionRepository.insert(newSession);
        return newSession;
    }

    public Session joinSession(JoinRequest joinRequest) {
        String newUser = joinRequest.getUserId();
        Optional<Session> onGoing = sessionRepository.findById(joinRequest.getSessionId());
        if (onGoing.isPresent() &&
            onGoing.get().getId().equals(joinRequest.getSessionId()) &&
            onGoing.get().getJoinToken().equals(joinRequest.getJoinToken())){
            Session session = onGoing.get();
            if (!session.getUsers().contains(newUser)) {
                newUser = new ObjectId() + "-" + newUser;
                session.getUsers().add(newUser);
                sessionRepository.save(session);
            }
            session.setUsers(Collections.singleton(newUser));
            return session;
        } else {
           return null;
        }
    }
}
