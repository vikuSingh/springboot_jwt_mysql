package com.vikas.springboot_jwt_mysql.controller;

import com.vikas.springboot_jwt_mysql.config.JwtTokenUtil;
import com.vikas.springboot_jwt_mysql.dto.JwtRequest;
import com.vikas.springboot_jwt_mysql.dto.JwtResponse;
import com.vikas.springboot_jwt_mysql.dto.UserDto;
import com.vikas.springboot_jwt_mysql.service.JwtUserDetailsService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.expire_in}")
    private long expireIn;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthencationToken(@RequestBody JwtRequest jwtRequest, HttpServletRequest request) throws Exception {
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
         final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
         final String token = jwtTokenUtil.generateToken(userDetails);
         JwtResponse jwtResponse = new JwtResponse(token, expireIn*1000, UUID.randomUUID().toString());
         return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userDetailsService.save(userDto));
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(new JwtResponse(refreshTokenValue(request)));
    }

    private String refreshTokenValue(HttpServletRequest request) {
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
        System.out.println(claims);
        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        String refreshToken = jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
        return refreshToken;
    }
    private Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e) {
            throw new Exception("USER DISABLED", e);
        }catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
