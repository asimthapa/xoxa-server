package com.sudocat.xoxaserver.repository;

import com.sudocat.xoxaserver.domain.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    Optional<Message> findBySessionId(String sessionId);
}
