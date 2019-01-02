package com.jd.graduation.controller;

import com.jd.graduation.model.LoginModel;
import com.jd.graduation.serviceimpl.UserServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Api(description = "登录登出注册")
public class LoginController extends BaseController {
    private final UserServiceImpl userService;

    @Autowired
    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @ApiOperation(value = "用户登录", notes = "获取设置用户登录信息")
    public ReturnMap login(HttpServletResponse response, @RequestBody @Valid LoginModel model){
        String key = userService.login(model.getAccountName(), model.getAccountPassword());

        if (key != null){
            response.addCookie(new Cookie("token", key));
            return ReturnMap.ok(null);
        }
        return ReturnMap.wrongLogin();
    }

    @PostMapping("/logout")
    public ReturnMap logout(HttpServletRequest request){
        String key = getHeaderAuthorization(request);
        boolean result = userService.logout(key);
        return ReturnMap.ok(null);
    }

    @PostMapping("/register")
    public ReturnMap register(){
        boolean result = userService.register();
        if (result){
            return ReturnMap.ok(null);
        }

        return ReturnMap.error("注册失败，请稍后重试");
    }
}