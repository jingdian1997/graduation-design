package com.jd.graduation.controller;

import com.jd.graduation.Impl.SysConfigServiceImpl;
import com.jd.graduation.service.AuthenticationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@Api(description = "系统设置")
public class SysConfigController extends BaseController{
    @Autowired
    private SysConfigServiceImpl sysConfigService;
    @Autowired
    private AuthenticationService authenticationService;

    // 读取修改配置相关，包括售后服务期限，运费相关，可写可不写
}