package com.jd.graduation.controller;

import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.Impl.UserFocusServiceImpl;
import com.jd.graduation.VO.FocusVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/focus")
@Api(description = "我的关注")
public class UserFocusController extends BaseController{
    @Autowired
    private UserFocusServiceImpl userFocusService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/list")
    public ReturnMap list(HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        List<FocusVO> list;
        try {
            list = userFocusService.listByUid(user.getId());
        } catch (Exception e){
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(list);
    }

    @PostMapping("/focus/{bid}")
    public ReturnMap focus(@PathVariable("bid") Integer bid, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            userFocusService.focusOrDelete(bid, user.getId());
        } catch (Exception e){
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }

    @GetMapping("/ifFocused/{bid}")
    public ReturnMap ifFocused(@PathVariable("bid") Integer bid, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            boolean is = userFocusService.ifFocused(bid, user.getId());
            return ReturnMap.ok(is);
        } catch (Exception e){
            return ReturnMap.error(e.getMessage());
        }
    }
}