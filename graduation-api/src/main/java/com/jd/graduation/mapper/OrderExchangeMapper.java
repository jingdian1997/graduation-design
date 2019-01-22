package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.OrderExchangeDO;
import com.jd.graduation.VO.OrderExchangeVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderExchangeMapper extends BaseMapper<OrderExchangeDO> {
    @Select("SELECT o.*, u.nickname, b.`name` as bname FROM (`order_exchange` AS o " +
            "LEFT JOIN `user` AS u ON o.uid = u.id) " +
            "LEFT JOIN book AS b ON o.bid = b.id")
    List<OrderExchangeVO> getAll();

    @Select("SELECT o.*, u.nickname, b.`name` as bname FROM (`order_exchange` AS o " +
            "LEFT JOIN `user` AS u ON o.uid = u.id) " +
            "LEFT JOIN book AS b ON o.bid = b.id WHERE o.uid = #{uid}")
    List<OrderExchangeVO> getByUid(@Param("uid") Integer uid);
}