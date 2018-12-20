package com.jd.graduation.controller;

import com.jd.graduation.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping("/index")
    public User test(){
        User user = new User();
        user.setId(100);
        return user;
    }
}