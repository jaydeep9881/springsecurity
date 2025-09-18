package com.example.springsecurity.controller;

import com.example.springsecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    List<Student> students= new ArrayList<>(List.of(new Student(1,"jaydeep","A",21),new Student(1,"jayraj","A",22))
    );
    @GetMapping("student")
    public List<Student> getStudent(){
        return students;
    }
    @PostMapping("students")
    public void addStudent(@RequestBody Student s){
        students.add(s);
    }
    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }


}
