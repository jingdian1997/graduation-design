package com.jd.graduation.controller;

import com.jd.graduation.Impl.SysConfigServiceImpl;
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

}