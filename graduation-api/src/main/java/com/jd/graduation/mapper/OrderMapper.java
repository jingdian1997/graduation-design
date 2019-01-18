package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.OrderDO;
import com.jd.graduation.VO.OrderVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderDO> {
    @Select("SELECT o.*, a.address, a.recipient, a.tel FROM `order` as o " +
            "LEFT JOIN user_address as a ON o.aid = a.id WHERE o.uid = #{uid}")
    List<OrderVO> getList(@Param("uid") Integer uid);

    @Select("SELECT o.*, a.address, a.recipient, a.tel FROM `order` as o " +
            "LEFT JOIN user_address as a ON o.aid = a.id WHERE o.uid = #{uid} and o.id = #{oid}")
    OrderVO getOne(@Param("uid") Integer uid, @Param("oid") Integer oid);
}