package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.OrderDetailDO;
import com.jd.graduation.VO.OrderDetailVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderDetailMapper extends BaseMapper<OrderDetailDO> {
    @Update("update order_detail set flag = #{flag} where oid = #{oid}")
    void updateFlagByOid(@Param("oid") Integer oid, @Param("flag") Integer flag);

    @Select("select od.*, b.name as bname from order_detail as od left join book as b on " +
            "b.id = od.bid where od.oid = #{oid}")
    List<OrderDetailVO> getByOid(@Param("oid") Integer oid);
}