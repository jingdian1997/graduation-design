package com.jd.graduation.controller;

import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.RefundCreateDTO;
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
public class OrderExchangeController extends BaseController {
    @Autowired
    private OrderExchangeServiceImpl orderExchangeService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/create")
    public ReturnMap create(@RequestBody @Valid RefundCreateDTO dto, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            orderExchangeService.create(dto, user.getId());
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(null);
    }

    @PostMapping("/complete")
    public ReturnMap complete(@RequestBody Integer id, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            orderExchangeService.complete(id, user.getId());
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(null);
    }

    @GetMapping("/get")
    public ReturnMap get( HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        List<OrderExchangeVO> list = orderExchangeService.getByUid(user.getId());
        return ReturnMap.ok(list);
    }
}