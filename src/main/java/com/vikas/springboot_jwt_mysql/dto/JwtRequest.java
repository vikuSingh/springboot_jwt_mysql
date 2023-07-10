package com.vikas.springboot_jwt_mysql.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtRequest implements Serializable {
    private String username;
    private String password;
}
