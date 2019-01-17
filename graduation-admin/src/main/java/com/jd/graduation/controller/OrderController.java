package com.jd.graduation.controller;

import com.jd.graduation.Impl.OrderServiceImpl;
import com.jd.graduation.service.AuthenticationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Api(description = "订单管理")
public class OrderController extends BaseController{
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private AuthenticationService authenticationService;

}