package com.jd.graduation.controller;

import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.DTO.RefundPayDTO;
import com.jd.graduation.DTO.RefundRefuseDTO;
import com.jd.graduation.Impl.OrderExchangeServiceImpl;
import com.jd.graduation.VO.OrderExchangeVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/exchange")
@Api(description = "换货管理")
public class OrderExchangeController extends BaseController{
    @Autowired
    private OrderExchangeServiceImpl orderExchangeService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/approve")
    public ReturnMap approve(@RequestBody Integer id, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        try {
            orderExchangeService.approve(id);
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(null);
    }

    @PostMapping("/refuse")
    public ReturnMap refuse(@RequestBody @Valid RefundRefuseDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        try {
            orderExchangeService.refuse(dto);
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(null);
    }

    @PostMapping("/pay")
    public ReturnMap resend(@RequestBody @Valid Integer id, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        try {
            orderExchangeService.resend(id);
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(null);
    }

    @PostMapping("/pay")
    public ReturnMap pay(@RequestBody @Valid RefundPayDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        try {
            orderExchangeService.pay(dto);
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(null);
    }

    @GetMapping("/get")
    public ReturnMap get( HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        List<OrderExchangeVO> list = orderExchangeService.get();
        return ReturnMap.ok(list);
    }
}