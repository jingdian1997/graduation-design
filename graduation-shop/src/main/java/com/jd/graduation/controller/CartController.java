package com.jd.graduation.controller;

import com.jd.graduation.DO.CartDO;
import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.OrderCreateDTO;
import com.jd.graduation.Impl.CartServiceImpl;
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
@Api(description = "用户模块")
public class CartController extends BaseController{
    @Autowired
    private CartServiceImpl cartService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/addTo")
    public ReturnMap addTo(@RequestBody Integer bid, HttpServletRequest request) {
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

        List<CartDO> data = cartService.getAll(user.getId());
        return ReturnMap.ok(data);
    }

    @PostMapping("/createOrder")
    public ReturnMap createOrder(@RequestBody @Valid List<OrderCreateDTO> dtos, HttpServletRequest request){
        //TODO:订单生成
        return ReturnMap.ok(null);
    }
}