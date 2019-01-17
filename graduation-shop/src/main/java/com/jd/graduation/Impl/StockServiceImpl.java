package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.StockDO;
import com.jd.graduation.service.StockService;
import org.springframework.stereotype.Service;

@Service("StockServiceImpl")
public class StockServiceImpl extends StockService {
    public boolean reduceStock(Integer amount, Integer bid) {
        QueryWrapper<StockDO> wrapper = new QueryWrapper<>();
        wrapper.eq("bid", bid);
        StockDO stockDO = baseMapper.selectOne(wrapper);

        Integer now = stockDO.getStock();
        if (now < amount) {
            return false;
        }

        stockDO.setStock(now - amount);
        baseMapper.updateById(stockDO);

        return true;
    }

    public Integer getStock(Integer bid) {
        return baseMapper.getStockByBid(bid);
    }
}