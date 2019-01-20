package com.jd.graduation.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refund")
@Api(description = "退货管理")
public class OrderRefundController extends BaseController{
}