package com.jd.graduation.Impl;

import com.jd.graduation.DO.OrderDetailDO;
import com.jd.graduation.DO.OrderExchangeDO;
import com.jd.graduation.DTO.RefundCreateDTO;
import com.jd.graduation.service.OrderExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("OrderExchangeServiceImpl")
public class OrderExchangeServiceImpl extends OrderExchangeService {
    @Autowired
    private OrderDetailServiceImpl orderDetailService;

    public void create(RefundCreateDTO dto, Integer uid) throws Exception {
        OrderDetailDO orderDetailDO = orderDetailService.getOne(dto.getOdid());
        if (!orderDetailDO.getFlag().equals(4)){
            throw new Exception("未完成订单不可退货");
        }

        OrderExchangeDO orderExchangeDO = new OrderExchangeDO();

        orderExchangeDO.setBid(orderDetailDO.getBid());
        orderExchangeDO.setOdid(orderDetailDO.getId());
        orderExchangeDO.setUid(uid);
        orderExchangeDO.setAmount(dto.getAmount());
        orderExchangeDO.setReason(dto.getReason());
        orderExchangeDO.setFlag(0);
        orderExchangeDO.setCreateTime(new Date());

        baseMapper.insert(orderExchangeDO);
    }

    public void complete(Integer id, Integer uid) throws Exception {
        OrderExchangeDO refundDO = baseMapper.selectById(id);
        if (refundDO == null || !refundDO.getFlag().equals(2)) {
            throw new Exception("退货请求不可完成");
        }

        if (!refundDO.getUid().equals(uid)) {
            throw new Exception("不是你的退货请求");
        }

        refundDO.setFlag(3);
        refundDO.setCompleteTime(new Date());

        baseMapper.updateById(refundDO);
    }
}