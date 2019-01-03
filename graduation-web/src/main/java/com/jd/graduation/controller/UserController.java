package com.jd.graduation.controller;

import com.jd.graduation.entity.User;
import com.jd.graduation.model.ChangePasswordModel;
import com.jd.graduation.model.UserChangeInfoModel;
import com.jd.graduation.model.UserCreateModel;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.serviceimpl.UserServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(description = "用户管理")
public class UserController extends BaseController{
    private final UserServiceImpl userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, AuthenticationService authenticationService) {
        this.userService = userServiceImpl;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ReturnMap register(@RequestBody @Valid UserCreateModel userCreateModel){
        boolean result = userService.register(userCreateModel);
        if (result){
            return ReturnMap.ok(null);
        }

        return ReturnMap.error("注册失败，请稍后重试");
    }

    @PostMapping("/changePwd")
    public ReturnMap changePwd(@RequestBody @Valid ChangePasswordModel model, HttpServletRequest request) {
        String key = getHeaderAuthorization(request);
        User user = authenticationService.getUserEntity(key);
        if (user == null) {
            return ReturnMap.notLogin();
        }

        boolean result = userService.changePwd(model, user.getId());
        if (result){
            return ReturnMap.ok(null);
        }

        return ReturnMap.error("修改密码失败");
    }

    @PostMapping("/changeInfo")
    public ReturnMap changeInfo(UserChangeInfoModel model, HttpServletRequest request) {
        String key = getHeaderAuthorization(request);
        User user = authenticationService.getUserEntity(key);
        if (user == null) {
            return ReturnMap.notLogin();
        }

        boolean result = userService.changeInfo(model, user.getId());
        return ReturnMap.ok(null);
    }
}