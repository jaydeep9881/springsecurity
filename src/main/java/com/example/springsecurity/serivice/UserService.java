package com.example.springsecurity.serivice;

import com.example.springsecurity.model.Users;
import com.example.springsecurity.repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo repo;
    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder(12);
    public Users addUser(Users user) {
        user.setPassword(encoder.encode(user.getPassword()));
        Users save = repo.save(user);
        return save;
    }
}
