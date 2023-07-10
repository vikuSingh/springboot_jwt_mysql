package com.vikas.springboot_jwt_mysql.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {
    private String accessToken;
    private String tokenType = "Bearer";
    private long expireIn;
    private String refreshToken;

    public JwtResponse(){}

    public JwtResponse(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public JwtResponse(String accessToken, long expireIn, String refreshToken) {
        this.accessToken = accessToken;
        this.expireIn = expireIn;
        this.refreshToken = refreshToken;
    }
}
