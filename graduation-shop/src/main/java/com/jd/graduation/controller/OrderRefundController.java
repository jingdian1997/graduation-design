package com.jd.graduation.controller;

import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.RefundCreateDTO;
import com.jd.graduation.Impl.OrderRefundServiceImpl;
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
@RequestMapping("/refund")
@Api(description = "退货管理")
public class OrderRefundController extends BaseController {
    @Autowired
    private OrderRefundServiceImpl orderRefundService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/create")
    public ReturnMap create(@RequestBody @Valid RefundCreateDTO dto, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            orderRefundService.create(dto, user.getId());
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
            orderRefundService.complete(id, user.getId());
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(null);
    }
}