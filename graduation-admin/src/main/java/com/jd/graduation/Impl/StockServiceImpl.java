package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.StockDO;
import com.jd.graduation.DTO.StockCreateDTO;
import com.jd.graduation.service.StockService;
import org.springframework.stereotype.Service;

@Service("StockServiceImpl")
public class StockServiceImpl extends StockService {
    public void insert(StockCreateDTO dto) {
        StockDO stockDO = new StockDO();

        stockDO.setBid(dto.getBid());
        stockDO.setStock(dto.getStock());

        baseMapper.insert(stockDO);
    }

    public void addStock(Integer number, Integer bid) {
        QueryWrapper<StockDO> wrapper = new QueryWrapper<>();
        wrapper.eq("bid", bid);
        StockDO stockDO = baseMapper.selectOne(wrapper);

        Integer now = stockDO.getStock();
        stockDO.setStock(number + now);

        baseMapper.updateById(stockDO);
    }

    public boolean reduceStock(Integer number, Integer bid) {
        QueryWrapper<StockDO> wrapper = new QueryWrapper<>();
        wrapper.eq("bid", bid);
        StockDO stockDO = baseMapper.selectOne(wrapper);

        Integer now = stockDO.getStock();
        if (now < number) {
            return false;
        }

        stockDO.setStock(now - number);
        baseMapper.updateById(stockDO);

        return true;
    }
}