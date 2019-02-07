package com.jd.graduation.controller;

import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.DTO.OrderDeliverDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Api(description = "订单管理")
public class OrderController extends BaseController{
    @Autowired
    private OrderServiceImpl orderService;
    @Autowired
    private OrderDetailServiceImpl orderDetailService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/confirmOrder/{oid}")
    public ReturnMap confirmOrder(@PathVariable("oid") Integer oid, HttpServletRequest request){
        AdminDO admin = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (admin == null) {
            return ReturnMap.notLogin();
        }
        if (!admin.getRole().equals(1) && !admin.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            orderService.confirm(oid);
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }

    @PostMapping("/deliverOder")
    public ReturnMap deliverOder(@RequestBody OrderDeliverDTO dto, HttpServletRequest request) {
        AdminDO admin = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (admin == null) {
            return ReturnMap.notLogin();
        }
        if (!admin.getRole().equals(1) && !admin.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            orderService.deliver(dto.getOid(), dto.getDeliverNo());
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }

    @GetMapping("/list/{status}")
    public ReturnMap list(@PathVariable("status") Integer status, HttpServletRequest request) {
        AdminDO admin = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (admin == null) {
            return ReturnMap.notLogin();
        }
        if (!admin.getRole().equals(1) && !admin.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        List<OrderVO> list = orderService.getListByStatus(status);
        return ReturnMap.ok(list);
    }

    @GetMapping("/one/{oid}")
    public ReturnMap one(@PathVariable("oid") Integer oid, HttpServletRequest request) {
        AdminDO admin = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (admin == null) {
            return ReturnMap.notLogin();
        }
        if (!admin.getRole().equals(1) && !admin.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        Map<String, Object> map = new HashMap<>();
        OrderVO one = orderService.getOne(oid);
        List<OrderDetailVO> list = orderDetailService.getByOid(one.getId());

        map.put("order", one);
        map.put("detail", list);
        return ReturnMap.ok(map);
    }

    @PostMapping("/deleteOrder/{oid}")
    public ReturnMap deleteOrder(@PathVariable("oid") Integer oid, HttpServletRequest request){
        AdminDO admin = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (admin == null) {
            return ReturnMap.notLogin();
        }
        if (!admin.getRole().equals(1) && !admin.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            orderService.deleteOrder(oid);
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }
}