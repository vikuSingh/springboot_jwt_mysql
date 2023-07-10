package com.vikas.springboot_jwt_mysql.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println(" JwtAuthenticationEntryPoint ");
        final String expiredMsg = (String) request.getAttribute("tokenExpired");
        final String msg = (expiredMsg != null) ? expiredMsg : "Unauthorized";
        byte[] body = new ObjectMapper().writeValueAsBytes(Collections.singletonMap(HttpServletResponse.SC_UNAUTHORIZED, msg));
        response.getOutputStream().write(body);
    }

}
