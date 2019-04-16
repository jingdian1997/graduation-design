package com.jd.graduation.controller;

import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.Impl.StaticServiceImpl;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/static")
@Api(description = "销售统计")
public class StaticController extends BaseController{
    @Autowired
    private StaticServiceImpl staticService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/price")
    public ReturnMap price(HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(6) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        Map<String, Object> list = staticService.getPriceStatics();
        return ReturnMap.ok(list);
    }

    @GetMapping("/amount")
    public ReturnMap amount(HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(6) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        Map<String, Object> list = staticService.getAmountStatics();
        return ReturnMap.ok(list);
    }

    @GetMapping("/month")
    public ReturnMap month(HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(6) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        Map<String, Object> list = staticService.getMonth();
        return ReturnMap.ok(list);
    }
}