package com.jd.graduation.Impl;

import com.jd.graduation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("OrderServiceImpl")
public class OrderServiceImpl extends OrderService {
    @Autowired
    private OrderDetailServiceImpl orderDetailService;
}