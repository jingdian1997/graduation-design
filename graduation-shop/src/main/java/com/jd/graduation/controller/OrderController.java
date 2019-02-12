package com.jd.graduation.controller;

import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.OrderCreateDTO;
import com.jd.graduation.Impl.OrderDetailServiceImpl;
import com.jd.graduation.Impl.OrderServiceImpl;
import com.jd.graduation.VO.OrderDetailVO;
import com.jd.graduation.VO.OrderVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        try {
            Integer oid = orderService.create(dto, user.getId());
            return ReturnMap.ok(oid);
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
    }

    @PostMapping("/cancelOrder/{oid}")
    public ReturnMap cancelOder(@PathVariable("oid") Integer oid, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            orderService.cancel(oid, user.getId());
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }

    @PostMapping("/payOrder/{oid}")
    public ReturnMap payOrder(@PathVariable("oid") Integer oid, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            orderService.pay(oid, user.getId());
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }

    @PostMapping("/completeOrder/{oid}")
    public ReturnMap completeOrder(@PathVariable("oid") Integer oid, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            orderService.complete(oid, user.getId());
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }

    @GetMapping("/list")
    public ReturnMap list(HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        List<OrderVO> list = orderService.getList(user.getId());
        return ReturnMap.ok(list);
    }

    @GetMapping("/one/{oid}")
    public ReturnMap one(@PathVariable("oid") Integer oid, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        Map<String, Object> map = new HashMap<>();
        OrderVO one = orderService.getOne(user.getId(), oid);
        List<OrderDetailVO> list = orderDetailService.getByOid(one.getId());

        map.put("order", one);
        map.put("detail", list);
        return ReturnMap.ok(map);
    }

    @PostMapping("/deleteOrder/{oid}")
    public ReturnMap deleteOrder(@PathVariable("oid") Integer oid, HttpServletRequest request){
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            orderService.deleteOrder(oid, user.getId());
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }
}