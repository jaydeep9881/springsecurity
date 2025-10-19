package com.example.springsecurity.controller;

import com.example.springsecurity.dao.LoginRequest;
import com.example.springsecurity.serivice.JwtService;
import com.mysql.cj.protocol.AuthenticationProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
public class Home {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/home")
    public String home(HttpServletRequest request) {

        return "Home page";
    }
    @RequestMapping("about")
    public String about(HttpServletRequest request){
        return "this is about page "+"Session id ="+request.getSession().getId();
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        System.out.println(authentication.getCredentials());
        return jwtService.generateToken(user.getUsername());
//        if(authentication.isAuthenticated()){
//        return "successfully logged in";}
//        else{
//            return "login failed";
//        }
    }


}
