package com.jd.graduation.controller;

import com.jd.graduation.entity.User;
import com.jd.graduation.service.UserService;
import com.jd.graduation.util.ReturnMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RedisTemplate<String, Object> template;

    @Autowired
    public UserController(UserService userService, RedisTemplate<String, Object> template) {
        this.userService = userService;
        this.template = template;
    }

    @GetMapping("/")
    public ReturnMap list(){
        List<User> data = userService.selectList();
        return ReturnMap.ok(data);
    }

    @GetMapping("/{id}")
    public ReturnMap findById(@PathVariable("id") Integer id){
        User user = userService.selectById(id);
        template.opsForValue().set(user.getAccountName(), user);
        User data = (User) template.opsForValue().get(user.getAccountName());
        return ReturnMap.ok(data);
    }
}