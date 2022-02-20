package com.sudocat.xoxaserver.controller;

import com.sudocat.xoxaserver.domain.Message;
import com.sudocat.xoxaserver.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    private final MessageService messageService;
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/{sessionId}/message")
    @SendTo({"/topic/{sessionId}/message"})
    public Message sendMessage(Message message) {
        logger.info("Received message: " + message);
        messageService.saveMessage(message);
        return message;
    }
}
