package com.sudocat.xoxaserver.domain;

public class JoinRequest {
    private String sessionId;
    private String joinToken;
    private String userId;

    public JoinRequest() {
    }

    public JoinRequest(String sessionId, String jointToken, String userId) {
        this.sessionId = sessionId;
        this.joinToken = jointToken;
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getJoinToken() {
        return joinToken;
    }

    public void setJoinToken(String joinToken) {
        this.joinToken = joinToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
