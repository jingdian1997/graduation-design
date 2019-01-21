package com.jd.graduation.Impl;

import com.jd.graduation.DO.OrderRefundDO;
import com.jd.graduation.DTO.RefundPayDTO;
import com.jd.graduation.DTO.RefundRefuseDTO;
import com.jd.graduation.service.OrderRefundService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("OrderRefundServiceImpl")
public class OrderRefundServiceImpl extends OrderRefundService {
    public void approve(Integer id) throws Exception{
        OrderRefundDO refundDO = baseMapper.selectById(id);
        if (refundDO == null || !refundDO.getFlag().equals(0)) {
            throw new Exception("退货请求不可批准");
        }

        refundDO.setFlag(1);
        refundDO.setReplyTime(new Date());

        baseMapper.updateById(refundDO);
    }

    public void refuse(RefundRefuseDTO dto) throws Exception{
        OrderRefundDO refundDO = baseMapper.selectById(dto.getId());
        if (refundDO == null || !refundDO.getFlag().equals(0)) {
            throw new Exception("退货请求不可拒绝");
        }

        refundDO.setFlag(-1);
        refundDO.setReplyTime(new Date());
        refundDO.setRefuseReason(dto.getRefuseReason());

        baseMapper.updateById(refundDO);
    }

    public void pay(RefundPayDTO dto) throws Exception {
        OrderRefundDO refundDO = baseMapper.selectById(dto.getId());
        if (refundDO == null || !refundDO.getFlag().equals(1)) {
            throw new Exception("退货请求不可打款");
        }

        refundDO.setFlag(2);
        refundDO.setPay(dto.getPay());
        refundDO.setPayTime(new Date());

        baseMapper.updateById(refundDO);
    }
}