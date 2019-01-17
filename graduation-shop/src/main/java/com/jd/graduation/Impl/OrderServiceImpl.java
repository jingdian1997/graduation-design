package com.jd.graduation.Impl;

import com.jd.graduation.DO.OrderDO;
import com.jd.graduation.DTO.OrderCreateDTO;
import com.jd.graduation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("OrderServiceImpl")
public class OrderServiceImpl extends OrderService {
    @Autowired
    private OrderDetailServiceImpl orderDetailService;

    @Transactional(rollbackFor = Exception.class)
    public void create(OrderCreateDTO dto, Integer uid) {
        OrderDO orderDO = new OrderDO();
        orderDO.setUid(uid);
        orderDO.setAid(dto.getAddressId());
        orderDO.setStatus(0);
        orderDO.setCreateTime(new Date());
        baseMapper.insert(orderDO);

        double price = orderDetailService.createList(dto.getCartIds(), uid, orderDO.getId());
        //TODO: sys_config
        double freight = 6.0;
        if (price >= 50){
            freight = 0;
        }

        orderDO.setFreight(freight);
        orderDO.setPrice(price + freight);
        baseMapper.updateById(orderDO);
    }
}