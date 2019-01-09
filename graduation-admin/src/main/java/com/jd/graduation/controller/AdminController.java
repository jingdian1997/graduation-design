package com.jd.graduation.controller;

import com.jd.graduation.model.request.ChangePasswordModel;
import com.jd.graduation.service.AuthenticationService;
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
@RequestMapping("/admin")
@Api(description = "管理员账户管理")
public class AdminController extends BaseController{
    private final AuthenticationService authenticationService;

    @Autowired
    public AdminController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/changePwd")
    public ReturnMap changePwd(@RequestBody @Valid ChangePasswordModel model, HttpServletRequest request) {
//        AdminConfigVO adminConfigVO = authenticationService.getAdmin(getHeaderAuthorization(request));
//        if (adminConfigVO == null) {
//            return ReturnMap.notLogin();
//        }
//
//        boolean result = systemConfigDetailService.changePwd(model);
//        if (result){
//            return ReturnMap.ok(null);
//        }

        return ReturnMap.error("修改密码失败");
    }
}