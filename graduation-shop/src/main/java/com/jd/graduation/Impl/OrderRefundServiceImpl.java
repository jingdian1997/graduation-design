package com.jd.graduation.Impl;

import com.jd.graduation.DO.OrderDetailDO;
import com.jd.graduation.DO.OrderRefundDO;
import com.jd.graduation.DTO.RefundCreateDTO;
import com.jd.graduation.VO.OrderRefundVO;
import com.jd.graduation.service.OrderRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("OrderRefundServiceImpl")
public class OrderRefundServiceImpl extends OrderRefundService {
    @Autowired
    private OrderDetailServiceImpl orderDetailService;

    public void create(RefundCreateDTO dto, Integer uid) throws Exception {
        OrderDetailDO orderDetailDO = orderDetailService.getOne(dto.getOdid());
        if (!orderDetailDO.getFlag().equals(4)){
            throw new Exception("未完成订单不可退货");
        }

        double price = orderDetailDO.getPrice();
        Integer amount = dto.getAmount();

        OrderRefundDO orderRefundDO = new OrderRefundDO();

        orderRefundDO.setBid(orderDetailDO.getBid());
        orderRefundDO.setOdid(orderDetailDO.getId());
        orderRefundDO.setUid(uid);
        orderRefundDO.setAmount(amount);
        orderRefundDO.setPrice(price * amount);
        orderRefundDO.setReason(dto.getReason());

        orderRefundDO.setType(dto.getType());
        orderRefundDO.setFlag(0);
        orderRefundDO.setCreateTime(new Date());

        baseMapper.insert(orderRefundDO);
    }

//    public void complete(Integer orid, Integer uid) throws Exception {
//        OrderRefundDO refundDO = baseMapper.selectById(orid);
//        if (refundDO == null || !refundDO.getFlag().equals(2)) {
//            throw new Exception("退货请求不可完成");
//        }
//
//        if (!refundDO.getUid().equals(uid)) {
//            throw new Exception("不是你的退货请求");
//        }
//
//        refundDO.setFlag(3);
//        refundDO.setCompleteTime(new Date());
//
//        baseMapper.updateById(refundDO);
//    }

    public List<OrderRefundVO> getByUid(Integer uid) {
        return baseMapper.getByUid(uid);
    }
}