package com.jd.graduation.controller;

import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.DTO.AdminChangeInfoDTO;
import com.jd.graduation.DTO.AdminCreateDTO;
import com.jd.graduation.DTO.ChangePasswordDTO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.Impl.AdminServiceImpl;
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
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AdminServiceImpl adminService;

    @PostMapping("/create")
    public ReturnMap create(@RequestBody @Valid AdminCreateDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        adminService.create(dto);
        return ReturnMap.ok(null);
    }

    @PostMapping("/changePwd")
    public ReturnMap changePwd(@RequestBody @Valid ChangePasswordDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        String message = adminService.changePwd(adminDO.getId(), dto);
        if (message == null) {
            return ReturnMap.ok(null);
        }

        return ReturnMap.error(message);
    }

    @PostMapping("/changeInfo")
    public ReturnMap changeInfo(@RequestBody @Valid AdminChangeInfoDTO dto, HttpServletRequest request){
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        adminService.changeInfo(dto, adminDO.getId());
        return ReturnMap.ok(null);
    }
}