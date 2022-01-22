package com.sudocat.xoxaserver.controller;

import com.sudocat.xoxaserver.domain.Message;
import com.sudocat.xoxaserver.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/{sessionId}/message")
    @SendTo({"/topic/{sessionId}/message"})
    public Message sendMessage(Message message) {
        messageService.saveMessage(message);
        return message;
    }
}
