package com.jd.graduation.Impl;

import com.jd.graduation.DO.OrderExchangeDO;
import com.jd.graduation.DO.OrderRefundDO;
import com.jd.graduation.DTO.RefundPayDTO;
import com.jd.graduation.DTO.RefundRefuseDTO;
import com.jd.graduation.VO.OrderExchangeVO;
import com.jd.graduation.service.OrderExchangeService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("OrderExchangeServiceImpl")
public class OrderExchangeServiceImpl extends OrderExchangeService {
    public void approve(Integer id) throws Exception{
        OrderExchangeDO refundDO = baseMapper.selectById(id);
        if (refundDO == null || !refundDO.getFlag().equals(0)) {
            throw new Exception("换货请求不可批准");
        }

        refundDO.setFlag(1);
        refundDO.setReplyTime(new Date());

        baseMapper.updateById(refundDO);
    }

    public void refuse(RefundRefuseDTO dto) throws Exception{
        OrderExchangeDO refundDO = baseMapper.selectById(dto.getId());
        if (refundDO == null || !refundDO.getFlag().equals(0)) {
            throw new Exception("换货请求不可拒绝");
        }

        refundDO.setFlag(-1);
        refundDO.setReplyTime(new Date());
        refundDO.setRefuseReason(dto.getRefuseReason());

        baseMapper.updateById(refundDO);
    }

    public void resend(Integer id) throws Exception {
        OrderExchangeDO refundDO = baseMapper.selectById(id);
        if (refundDO == null || !refundDO.getFlag().equals(1)) {
            throw new Exception("换货请求不可重发送");
        }

        refundDO.setFlag(2);
        refundDO.setResendTime(new Date());

        baseMapper.updateById(refundDO);
    }

    public void pay(RefundPayDTO dto) throws Exception {
        OrderExchangeDO refundDO = baseMapper.selectById(dto.getId());
        if (refundDO == null || !refundDO.getFlag().equals(1) || !refundDO.getFlag().equals(2)) {
            throw new Exception("换货请求不可重发送");
        }

        refundDO.setPay(dto.getPay());
        refundDO.setPayTime(new Date());

        baseMapper.updateById(refundDO);
    }

    public List<OrderExchangeVO> get() {
        return baseMapper.getAll();
    }
}