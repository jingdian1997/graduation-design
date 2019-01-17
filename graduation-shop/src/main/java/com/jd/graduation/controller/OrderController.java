package com.jd.graduation.controller;

import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.OrderCreateDTO;
import com.jd.graduation.Impl.OrderDetailServiceImpl;
import com.jd.graduation.Impl.OrderServiceImpl;
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
@RequestMapping("/order")
@Api(description = "订单模块")
public class OrderController extends BaseController{
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OrderDetailServiceImpl orderDetailService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/createOrder")
    public ReturnMap createOrder(@RequestBody @Valid OrderCreateDTO dto, HttpServletRequest request){
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        orderService.create(dto, user.getId());
        return ReturnMap.ok(null);
    }
}