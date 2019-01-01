package com.jd.graduation.controller;

import com.jd.graduation.entity.User;
import com.jd.graduation.serviceimpl.UserServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(description = "用户管理")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    @ApiOperation(value = "获取用户列表", notes = "获取所有用户信息")
    public ReturnMap list(){
        List<User> data = userServiceImpl.selectList();
        return ReturnMap.ok(data);
    }
}