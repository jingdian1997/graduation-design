package com.jd.graduation.controller;

import com.jd.graduation.DTO.LoginDTO;
import com.jd.graduation.Impl.UserLoginServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(description = "用户登录相关")
public class UserLoginController extends BaseController {
    private final UserLoginServiceImpl userLoginService;

    @Autowired
    public UserLoginController(UserLoginServiceImpl userLoginService) {
        this.userLoginService = userLoginService;
    }

    @PostMapping("/login")
    public ReturnMap login(HttpServletResponse response, @RequestBody @Valid LoginDTO loginDTO) {
        String key = userLoginService.login(loginDTO.getAccount(), loginDTO.getPwd());

        if (key != null){
            System.out.println(key);
            response.addCookie(new Cookie("token", key));
            return ReturnMap.ok(null);
        }

        return ReturnMap.wrongLogin();
    }

    @PostMapping("/logout")
    public ReturnMap logout(HttpServletRequest request){
        String key = getHeaderAuthorization(request);
        userLoginService.logout(key);
        return ReturnMap.ok(null);
    }
}