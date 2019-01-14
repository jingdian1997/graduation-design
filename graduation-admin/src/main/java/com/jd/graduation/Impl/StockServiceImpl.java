package com.jd.graduation.Impl;

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
        stockDO.setFreeStock(dto.getStock());

        baseMapper.insert(stockDO);
    }
}