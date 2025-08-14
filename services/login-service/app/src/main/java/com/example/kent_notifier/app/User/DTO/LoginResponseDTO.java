package com.example.kent_notifier.app.User.DTO;

public class LoginResponseDTO {

    private String token;

    private Long expirationTime;

    public String getToken() {
        return this.token;
    }

    public Long getExpirationTime() {
        return this.expirationTime;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpiration(Long expiration) {
        this.expirationTime = expiration;
    }
}
