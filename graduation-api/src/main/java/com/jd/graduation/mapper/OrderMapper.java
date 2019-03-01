package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.OrderDO;
import com.jd.graduation.VO.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderDO> {
    List<OrderVO> getList(@Param("uid") Integer uid);

    @Select("SELECT o.*, a.address, a.recipient, a.tel, u.nickname FROM `order` as o " +
            "LEFT JOIN user as u ON u.id=o.uid " +
            "LEFT JOIN user_address as a ON o.aid = a.id WHERE o.status = #{status}")
    List<OrderVO> getListByStatus(@Param("status") Integer status);
}