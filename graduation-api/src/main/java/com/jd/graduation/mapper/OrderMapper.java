package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.OrderDO;
import com.jd.graduation.VO.OrderVO;
import com.jd.graduation.VO.StaticsMonthVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderMapper extends BaseMapper<OrderDO> {
    @Select("SELECT o.*, u.nickname FROM `order` as o LEFT JOIN user as u ON u.id=o.uid")
    List<OrderVO> getListByStatus(@Param("status") Integer status);

    @Select("SELECT SUM(pay) as `value`, DATE_FORMAT(pay_time, '%Y-%m') AS name " +
            "FROM `order` WHERE `status`>0 GROUP BY name")
    List<StaticsMonthVO> getStaticsByMonth();

    @Select("select SUM(pay) from `order` WHERE `status`>0")
    Double allPay();
}