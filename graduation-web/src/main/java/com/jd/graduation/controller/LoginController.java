package com.jd.graduation.controller;

import com.jd.graduation.serviceimpl.UserServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(description = "登录登出注册")
public class LoginController {
    private final UserServiceImpl userService;

    @Autowired
    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "获取设置用户登录信息")
    public ReturnMap login(HttpServletResponse response){
        //TODO:登录参数封装，缺少参数
        boolean result = userService.login(null, null);
        return ReturnMap.ok(null);
    }

    @PostMapping("/logout")
    public ReturnMap logout(HttpServletRequest request){
        boolean result = userService.logout("");
        return ReturnMap.ok(null);
    }

    @PostMapping("/register")
    public ReturnMap register(){
        return ReturnMap.ok(null);
    }
}