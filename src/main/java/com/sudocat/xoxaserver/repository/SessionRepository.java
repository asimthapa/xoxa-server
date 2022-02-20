package com.sudocat.xoxaserver.repository;

import com.sudocat.xoxaserver.domain.ChatSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends MongoRepository<ChatSession, String> {

}
