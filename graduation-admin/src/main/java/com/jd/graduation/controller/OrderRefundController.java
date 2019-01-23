package com.jd.graduation.controller;

import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.DTO.RefundPayDTO;
import com.jd.graduation.DTO.RefundRefuseDTO;
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

    @PostMapping("/approve")
    public ReturnMap approve(@RequestBody Integer id, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(3) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            orderRefundService.approve(id);
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
        if (!adminDO.getRole().equals(3) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            orderRefundService.refuse(dto);
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(null);
    }

    @PostMapping("/deal")
    public ReturnMap deal(@RequestBody @Valid RefundPayDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(3) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            orderRefundService.deal(dto);
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
        if (!adminDO.getRole().equals(3) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        List<OrderRefundVO> list = orderRefundService.get();
        return ReturnMap.ok(list);
    }
}