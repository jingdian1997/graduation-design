package com.jd.graduation.controller;

import com.jd.graduation.DTO.LoginDTO;
import com.jd.graduation.Impl.AdminServiceImpl;
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
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Api(description = "登录登出")
public class AdminLoginController extends BaseController{
    @Autowired
    private AdminServiceImpl userService;

    @PostMapping("/login")
    public ReturnMap login(HttpServletResponse response, @RequestBody @Valid LoginDTO loginDTO){
        Map<String, Object> map = userService.login(loginDTO.getAccount(), loginDTO.getPwd());
        String key = (String) map.get("key");

        if (key != null){
            System.out.println(key);
            response.addCookie(new Cookie("token", key));
            return ReturnMap.ok(map);
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