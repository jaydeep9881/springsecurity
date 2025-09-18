package com.example.springsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Home {

    @RequestMapping("/home")
    public String home(HttpServletRequest request) {

        return "Home page"+"seesion id"+request.getSession().getId();
    }
    @RequestMapping("about")
    public String about(HttpServletRequest request){
        return "this is about page "+"Session id ="+request.getSession().getId();
    }
}
