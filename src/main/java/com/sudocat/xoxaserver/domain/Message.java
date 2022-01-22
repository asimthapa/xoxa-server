package com.sudocat.xoxaserver.domain;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("messages")
public class Message {
    private String sessionId;
    private String sender;
    private String content;
    private String messageTime;

    public Message(String sessionId, String sender, String content, String messageTime) {
        this.sessionId = sessionId;
        this.sender = sender;
        this.content = content;
        this.messageTime = messageTime;
    }

    public Message() {
    }

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s", sessionId, sender, content, messageTime);
    }
}
