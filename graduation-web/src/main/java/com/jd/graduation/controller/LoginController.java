package com.jd.graduation.controller;

import com.jd.graduation.model.request.LoginModel;
import com.jd.graduation.serviceimpl.UserServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Api(description = "登录登出")
public class LoginController extends BaseController {
    private final UserServiceImpl userService;

    @Autowired
    public LoginController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
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
        userService.logout(key);
        return ReturnMap.ok(null);
    }
}