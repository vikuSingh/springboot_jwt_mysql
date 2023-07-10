package com.vikas.springboot_jwt_mysql.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloWorldController {

    @GetMapping("/test")
    public ResponseEntity<?> helloWorld(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok("Welcome to JWT Application.");
    }
}
