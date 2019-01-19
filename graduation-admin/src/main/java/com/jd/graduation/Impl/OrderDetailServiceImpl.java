package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.OrderDetailDO;
import com.jd.graduation.VO.OrderDetailVO;
import com.jd.graduation.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("OrderDetailServiceImpl")
public class OrderDetailServiceImpl extends OrderDetailService {
    public void confirmList(Integer oid) {
        baseMapper.updateFlagByOid(oid, 2);
    }

    public void deliverList(Integer oid) {
        baseMapper.updateFlagByOid(oid, 3);
    }

    public void deleteList(Integer oid) {
        QueryWrapper<OrderDetailDO> wrapper = new QueryWrapper<>();
        wrapper.eq("oid", oid);
        baseMapper.delete(wrapper);
    }

    public List<OrderDetailVO> getByOid(Integer oid) {
        return baseMapper.getByOid(oid);
    }
}