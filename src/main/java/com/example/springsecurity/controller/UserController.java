package com.example.springsecurity.controller;

import com.example.springsecurity.model.Users;
import com.example.springsecurity.serivice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService service;
    @PostMapping("register")
    public Users register(@RequestBody Users user){
       return service.addUser(user);
    }
}
