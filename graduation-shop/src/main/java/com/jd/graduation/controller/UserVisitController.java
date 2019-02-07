package com.jd.graduation.controller;

import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.Impl.UserVisitServiceImpl;
import com.jd.graduation.VO.VisitVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/visit")
@Api(description = "我的足迹")
public class UserVisitController extends BaseController{
    @Autowired
    private UserVisitServiceImpl userVisitService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/list")
    public ReturnMap list(HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        List<VisitVO> list;
        try {
            list = userVisitService.listByUid(user.getId());
        } catch (Exception e){
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(list);
    }

    @PostMapping("/create/{bid}")
    public ReturnMap create(@PathVariable("bid") Integer bid, HttpServletRequest request) {
        Integer uid;

        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            // 在这里，如果未登录访问，则访问记录为-1，游客
            uid = -1;
        } else {
            uid = user.getId();
        }

        try {
            userVisitService.create(uid, bid);
        } catch (Exception e){
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }

    @PostMapping("/delete")
    public ReturnMap delete(@RequestBody List<Integer> ids, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            userVisitService.delete(ids, user.getId());
        } catch (Exception e){
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }
}