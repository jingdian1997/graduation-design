package com.jd.graduation.controller;

import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.ChangePasswordDTO;
import com.jd.graduation.DTO.LoginDTO;
import com.jd.graduation.DTO.UserChangeMailDTO;
import com.jd.graduation.DTO.UserChangeTelDTO;
import com.jd.graduation.Impl.UserLoginServiceImpl;
import com.jd.graduation.service.AuthenticationService;
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
@RequestMapping("/user")
@Api(description = "用户登录相关")
public class UserLoginController extends BaseController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserLoginServiceImpl userLoginService;

    @PostMapping("/login")
    public ReturnMap login(HttpServletResponse response, @RequestBody @Valid LoginDTO loginDTO) {
        Map<String, Object> map = userLoginService.login(loginDTO.getAccount(), loginDTO.getPwd());
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
        userLoginService.logout(key);
        return ReturnMap.ok(null);
    }

    @PostMapping("/changePwd")
    public ReturnMap changePwd(@RequestBody @Valid ChangePasswordDTO dto, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        String message = userLoginService.changePwd(user.getId(), dto);
        if (message == null){
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }

    @PostMapping("/changeMail")
    public ReturnMap changeMail(@RequestBody @Valid UserChangeMailDTO dto, HttpServletRequest request) {
        String key = getHeaderAuthorization(request);

        UserLoginDO user = authenticationService.getUser(key);
        if (user == null) {
            return ReturnMap.notLogin();
        }

        String message = userLoginService.changeMail(dto, user.getId(), key);
        if (message == null){
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }

    @PostMapping("/changeTel")
    public ReturnMap changeTel(@RequestBody @Valid UserChangeTelDTO dto, HttpServletRequest request) {
        String key = getHeaderAuthorization(request);

        UserLoginDO user = authenticationService.getUser(key);
        if (user == null) {
            return ReturnMap.notLogin();
        }

        String message = userLoginService.changeTel(dto, user.getId(), key);
        if (message == null){
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }
}