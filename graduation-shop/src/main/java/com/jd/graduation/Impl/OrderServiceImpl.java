package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.OrderDO;
import com.jd.graduation.DO.SysConfigKey;
import com.jd.graduation.DTO.OrderCreateDTO;
import com.jd.graduation.VO.OrderVO;
import com.jd.graduation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("OrderServiceImpl")
public class OrderServiceImpl extends OrderService {
    @Autowired
    private OrderDetailServiceImpl orderDetailService;
    @Autowired
    private SysConfigServiceImpl sysConfigService;

    @Transactional(rollbackFor = Exception.class)
    public Integer create(OrderCreateDTO dto, Integer uid) throws Exception {
        OrderDO orderDO = new OrderDO();
        orderDO.setUid(uid);
        orderDO.setAid(dto.getAddressId());
        orderDO.setStatus(0);
        orderDO.setPay(0.0);
        orderDO.setCreateTime(new Date());
        baseMapper.insert(orderDO);

        double price = orderDetailService.createList(dto.getCartIds(), uid, orderDO.getId());

        double standardPrice = sysConfigService.getValueDouble(SysConfigKey.FREIGHT_FREE_PRICE);

        double freight = 0;
        if (price < standardPrice){
            freight = sysConfigService.getValueDouble(SysConfigKey.FREIGHT);
        }

        orderDO.setFreight(freight);
        orderDO.setPrice(price);
        baseMapper.updateById(orderDO);

        return orderDO.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancel(Integer oid, Integer uid) throws Exception{
        OrderDO orderDO = baseMapper.selectById(oid);
        if (orderDO == null || !orderDO.getUid().equals(uid)){
            throw new Exception("订单不存在");
        }

        if (!orderDO.getStatus().equals(0) && !orderDO.getStatus().equals(1)){
            throw new Exception("订单已经不可取消");
        }

        orderDetailService.cancelList(oid);

        orderDO.setStatus(-1);
        orderDO.setPay(0.0);
        orderDO.setCancelTime(new Date());
        baseMapper.updateById(orderDO);
    }

    @Transactional(rollbackFor = Exception.class)
    public void pay(Integer oid, Integer uid) throws Exception{
        OrderDO orderDO = baseMapper.selectById(oid);
        if (orderDO == null || !orderDO.getUid().equals(uid)){
            throw new Exception("订单不存在");
        }

        if (!orderDO.getStatus().equals(0)){
            throw new Exception("订单是不可支付状态");
        }

        orderDetailService.payList(oid);

        orderDO.setStatus(1);
        orderDO.setPay(orderDO.getFreight() + orderDO.getPrice());
        orderDO.setPayTime(new Date());
        baseMapper.updateById(orderDO);
    }

    @Transactional(rollbackFor = Exception.class)
    public void complete(Integer oid, Integer uid) throws Exception{
        OrderDO orderDO = baseMapper.selectById(oid);
        if (orderDO == null || !orderDO.getUid().equals(uid)){
            throw new Exception("订单不存在");
        }

        if (!orderDO.getStatus().equals(3)){
            throw new Exception("订单是不可完成状态");
        }

        orderDetailService.completeList(oid);

        orderDO.setStatus(4);
        orderDO.setCompleteTime(new Date());
        baseMapper.updateById(orderDO);
    }

    public List<OrderVO> getList(Integer uid) {
        return baseMapper.getList(uid);
    }

    public OrderDO getOne(Integer uid, Integer oid) throws Exception {
        OrderDO orderDO = baseMapper.selectById(oid);
        if (!orderDO.getUid().equals(uid)) {
            throw new Exception("不是你的订单");
        }
        return orderDO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(Integer oid, Integer uid) throws Exception{
        OrderDO orderDO = baseMapper.selectById(oid);
        if (orderDO == null || !orderDO.getUid().equals(uid)){
            throw new Exception("订单不存在");
        }

        if (!orderDO.getStatus().equals(-1)){
            throw new Exception("订单不可删除");
        }

        orderDetailService.deleteList(oid);
        baseMapper.deleteById(oid);
    }
}