package com.vikas.springboot_jwt_mysql.service;

import com.vikas.springboot_jwt_mysql.dto.UserDto;
import com.vikas.springboot_jwt_mysql.model.UserData;
import com.vikas.springboot_jwt_mysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${jwt.expire_in}")
    private long expireIn;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = userRepository.findByUsername(username);
        if (userData == null) {
            throw new UsernameNotFoundException("User not found with username: "+ username);
        }
        return new User(userData.getUsername(), userData.getPassword(), new ArrayList<>());
    }

    public UserData save(UserDto userDto) {
        UserData userData = new UserData();
        userData.setUsername(userDto.getUsername());
        userData.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(userData);
    }

}
