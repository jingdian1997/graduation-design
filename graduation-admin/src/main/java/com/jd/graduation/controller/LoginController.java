package com.jd.graduation.controller;

import com.jd.graduation.model.LoginUserModel;
import com.jd.graduation.serviceimpl.UserServiceImplAdmin;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Api(description = "登录登出注册")
public class LoginController {
    private final UserServiceImplAdmin userService;

    @Autowired
    public LoginController(UserServiceImplAdmin userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "获取设置用户登录信息")
    public ReturnMap login(HttpServletResponse response, @Valid LoginUserModel model){
//        String key = userService.login(model.getAccountName(), model.getAccountPassword());
//
//        if (key != null){
//            response.addCookie(new Cookie("token", key));
//            return ReturnMap.ok(null);
//        }
        return ReturnMap.wrongLogin();
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