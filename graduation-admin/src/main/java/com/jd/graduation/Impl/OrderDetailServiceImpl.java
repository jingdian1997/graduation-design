package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    public IPage<OrderDetailDO> getList(int page, int size) {
        Page<OrderDetailDO> doPage = new Page<>(page, size);
        QueryWrapper<OrderDetailDO> wrapper = new QueryWrapper<>();
        wrapper.eq("flag", 4);
        return baseMapper.selectPage(doPage, wrapper);
    }

    public IPage<OrderDetailDO> getListByBid(int page, int size, int bid) {
        Page<OrderDetailDO> doPage = new Page<>(page, size);
        QueryWrapper<OrderDetailDO> wrapper = new QueryWrapper<>();
        wrapper.eq("flag", 4);
        wrapper.eq("bid", bid);
        return baseMapper.selectPage(doPage, wrapper);
    }
}