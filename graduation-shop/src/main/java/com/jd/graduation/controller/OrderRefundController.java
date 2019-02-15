package com.jd.graduation.controller;

import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.RefundCreateDTO;
import com.jd.graduation.Impl.OrderRefundServiceImpl;
import com.jd.graduation.VO.OrderRefundVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/get")
    public ReturnMap get(HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        List<OrderRefundVO> list = orderRefundService.getByUid(user.getId());
        return ReturnMap.ok(list);
    }
}