package com.vikas.springboot_jwt_mysql.repository;

import com.vikas.springboot_jwt_mysql.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserData, Long> {
    public UserData findByUsername(String username);
}
