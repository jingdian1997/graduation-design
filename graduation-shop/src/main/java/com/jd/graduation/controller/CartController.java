package com.jd.graduation.controller;

import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.CartChangeAmountDTO;
import com.jd.graduation.Impl.CartServiceImpl;
import com.jd.graduation.VO.CartVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
@Api(description = "购物车模块")
public class CartController extends BaseController{
    @Autowired
    private CartServiceImpl cartService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/addTo/{bid}")
    public ReturnMap addTo(@PathVariable("bid") Integer bid, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        String message = cartService.addToCart(user.getId(), bid);
        if (message == null){
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }

    @GetMapping("/getAll")
    public ReturnMap getAll(HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        List<CartVO> data = cartService.getAll(user.getId());
        return ReturnMap.ok(data);
    }

    @PostMapping("/delete/{id}")
    public ReturnMap delete(@PathVariable("id") Integer id, HttpServletRequest request){
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        String message = cartService.delete(id, user.getId());
        if (message == null) {
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }

    @PostMapping("/changAmount")
    public ReturnMap changAmount(@RequestBody @Valid CartChangeAmountDTO dto, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        String message = cartService.changeAmount(dto, user.getId());
        if (message == null) {
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }
}