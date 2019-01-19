package com.jd.graduation.Impl;

import com.jd.graduation.DO.OrderDO;
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

    @Transactional(rollbackFor = Exception.class)
    public void confirm(Integer oid) throws Exception{
        OrderDO orderDO = baseMapper.selectById(oid);
        if (orderDO == null){
            throw new Exception("订单不存在");
        }

        if (!orderDO.getStatus().equals(1)){
            throw new Exception("订单是不可确认状态");
        }

        orderDetailService.confirmList(oid);

        orderDO.setStatus(2);
        orderDO.setConfirmTime(new Date());
        baseMapper.updateById(orderDO);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deliver(Integer oid, String deliverNo) throws Exception {
        OrderDO orderDO = baseMapper.selectById(oid);
        if (orderDO == null){
            throw new Exception("订单不存在");
        }

        if (!orderDO.getStatus().equals(2)){
            throw new Exception("订单是不可发货状态");
        }

        orderDetailService.deliverList(oid);

        orderDO.setStatus(3);
        orderDO.setDeliverNo(deliverNo);
        orderDO.setDeliverTime(new Date());
        baseMapper.updateById(orderDO);
    }

    public List<OrderVO> getListByStatus(Integer status) {
        return baseMapper.getListByStatus(status);
    }

    public OrderVO getOne(Integer oid) {
        return baseMapper.getById(oid);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(Integer oid) throws Exception{
        OrderDO orderDO = baseMapper.selectById(oid);
        if (orderDO == null){
            throw new Exception("订单不存在");
        }

        if (!orderDO.getStatus().equals(-1)){
            throw new Exception("订单不可删除");
        }

        orderDetailService.deleteList(oid);
        baseMapper.deleteById(oid);
    }
}