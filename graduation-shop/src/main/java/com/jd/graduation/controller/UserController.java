package com.jd.graduation.controller;

import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.UserChangeInfoDTO;
import com.jd.graduation.DTO.UserCreateDTO;
import com.jd.graduation.Impl.UserServiceImpl;
import com.jd.graduation.VO.UserVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(description = "用户模块")
public class UserController extends BaseController{
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public ReturnMap register(@RequestBody @Valid UserCreateDTO dto){
        String result = userService.create(dto);
        if (result == null){
            return ReturnMap.ok(null);
        }

        return ReturnMap.error(result);
    }

    @PostMapping("/changeInfo")
    public ReturnMap changeInfo(@RequestBody @Valid UserChangeInfoDTO dto, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        userService.changeInfo(dto, user.getId());
        return ReturnMap.ok(null);
    }

    @GetMapping("/get")
    public ReturnMap get(HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        UserVO userVO = userService.get(user.getId());
        return ReturnMap.ok(userVO);
    }
}