package com.jd.graduation.controller;

import com.jd.graduation.Impl.CartServiceImpl;
import com.jd.graduation.service.AuthenticationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
@Api(description = "用户模块")
public class CartController extends BaseController{
    @Autowired
    private CartServiceImpl cartService;
    @Autowired
    private AuthenticationService authenticationService;

}