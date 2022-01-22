package com.sudocat.xoxaserver.service;

import com.sudocat.xoxaserver.domain.Message;
import com.sudocat.xoxaserver.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(Message message) {
        messageRepository.insert(message);
    }
}
