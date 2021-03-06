package com.sudocat.xoxaserver.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document("sessions")
public class ChatSession {
    @Id
    private String id;
    private String token;
    private Set<String> users;
    private String joinToken;

    public ChatSession() {
    }

    public ChatSession(String id, String token, Set<String> users, String joinToken) {
        this.id = id;
        this.token = token;
        this.users = users;
        this.joinToken = joinToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<String> getUsers() {
        return users;
    }

    public void setUsers(Set<String> users) {
        this.users = users;
    }

    public String getJoinToken() {
        return joinToken;
    }

    public void setJoinToken(String joinToken) {
        this.joinToken = joinToken;
    }

    @Override
    public String toString() {
        return String.format("id: %s\ntoken: %s\nusers: %s\njoinToken: %s\n", id, token, users.toString(), joinToken);
    }
}
