package com.example.todoserver.controller;

import com.example.todoserver.model.UserReq;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    @GetMapping
    public UserReq user(UserReq userReq){
        return userReq;
    }
}
