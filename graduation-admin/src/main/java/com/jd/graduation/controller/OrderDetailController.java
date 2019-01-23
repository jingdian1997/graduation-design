package com.jd.graduation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.DO.OrderDetailDO;
import com.jd.graduation.Impl.OrderDetailServiceImpl;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/detail")
@Api(description = "销售统计")
public class OrderDetailController extends BaseController {
    @Autowired
    private OrderDetailServiceImpl orderDetailService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/list/{page}/{size}")
    public ReturnMap list(@PathVariable("page") int page, @PathVariable("size") int size,
                          HttpServletRequest request) {
        AdminDO admin = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (admin == null) {
            return ReturnMap.notLogin();
        }
        if (!admin.getRole().equals(4) && !admin.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        IPage<OrderDetailDO> list = orderDetailService.getList(page, size);
        return ReturnMap.ok(list);
    }

    @GetMapping("/list/book/{bid}/{page}/{size}")
    public ReturnMap listByBid(@PathVariable("bid") int bid, @PathVariable("page") int page,
                               @PathVariable("size") int size, HttpServletRequest request) {
        AdminDO admin = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (admin == null) {
            return ReturnMap.notLogin();
        }
        if (!admin.getRole().equals(4) && !admin.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        IPage<OrderDetailDO> list = orderDetailService.getListByBid(page, size, bid);
        return ReturnMap.ok(list);
    }
}