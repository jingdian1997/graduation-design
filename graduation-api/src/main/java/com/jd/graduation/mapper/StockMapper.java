package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.StockDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface StockMapper extends BaseMapper<StockDO> {
    @Select("select stock from stock where bid = #{bid}")
    Integer getStockByBid(@Param("bid") Integer bid);
}