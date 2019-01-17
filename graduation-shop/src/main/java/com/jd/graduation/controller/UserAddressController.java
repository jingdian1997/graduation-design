package com.jd.graduation.controller;

import com.jd.graduation.DO.UserAddressDO;
import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.UserAddressCreateDTO;
import com.jd.graduation.DTO.UserAddressUpdateDTO;
import com.jd.graduation.Impl.UserAddressServiceImpl;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/address")
@Api(description = "收货地址模块")
public class UserAddressController extends BaseController {
    @Autowired
    private UserAddressServiceImpl userAddressService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/all")
    public ReturnMap all(HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        List<UserAddressDO> list = userAddressService.all(user.getId());
        return ReturnMap.ok(list);
    }

    @PostMapping("/add")
    public ReturnMap add(@RequestBody @Valid UserAddressCreateDTO dto, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        userAddressService.add(user.getId(), dto);
        return ReturnMap.ok(null);
    }

    @PostMapping("/setDefault")
    public ReturnMap setDefault(@RequestBody Integer id, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        String message = userAddressService.setDefault(id, user.getId());
        if (message == null) {
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }

    @PostMapping("/delete")
    public ReturnMap delete(@RequestBody Integer id, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        String message = userAddressService.delete(id, user.getId());
        if (message == null) {
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }

    @PostMapping("/update")
    public ReturnMap update(@RequestBody @Valid UserAddressUpdateDTO dto, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        String message = userAddressService.update(dto, user.getId());
        if (message == null) {
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }
}