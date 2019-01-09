package com.jd.graduation.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jd.graduation.DO.OrderDO;
import com.jd.graduation.entity.SaleOrder;
import com.jd.graduation.mapper.OrderMapper;

public abstract class OrderService extends ServiceImpl<OrderMapper, OrderDO> {
}