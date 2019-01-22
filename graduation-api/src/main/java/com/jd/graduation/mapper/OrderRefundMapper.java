package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.OrderRefundDO;
import com.jd.graduation.VO.OrderRefundVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderRefundMapper extends BaseMapper<OrderRefundDO> {
    @Select("SELECT o.*, u.nickname, b.`name` as bname FROM (`order_refund` AS o " +
            "LEFT JOIN `user` AS u ON o.uid = u.id) " +
            "LEFT JOIN book AS b ON o.bid = b.id")
    List<OrderRefundVO> getAll();

    @Select("SELECT o.*, u.nickname, b.`name` as bname FROM (`order_refund` AS o " +
            "LEFT JOIN `user` AS u ON o.uid = u.id) " +
            "LEFT JOIN book AS b ON o.bid = b.id WHERE o.uid = #{uid}")
    List<OrderRefundVO> getByUid(@Param("uid") Integer uid);
}