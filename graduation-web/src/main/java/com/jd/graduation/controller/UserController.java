package com.jd.graduation.controller;

import com.jd.graduation.entity.User;
import com.jd.graduation.serviceimpl.UserServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(description = "用户管理")
public class UserController {
    private final UserServiceImpl userService;
    private final RedisTemplate<String, Object> template;

    @Autowired
    public UserController(UserServiceImpl userService, RedisTemplate<String, Object> template) {
        this.userService = userService;
        this.template = template;
    }

    @GetMapping("/")
    @ApiOperation(value = "获取用户列表", notes = "获取所有用户信息")
    public ReturnMap list(){
        List<User> data = userService.selectList();
        return ReturnMap.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取指定用户", notes = "获取指定用户信息")
    public ReturnMap findById(@PathVariable("id") Integer id){
        User user = userService.selectById(id);
        template.opsForValue().set(user.getAccountName(), user);
        User data = (User) template.opsForValue().get(user.getAccountName());
        return ReturnMap.ok(data);
    }
}