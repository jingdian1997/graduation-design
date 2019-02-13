package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.OrderDetailDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface OrderDetailMapper extends BaseMapper<OrderDetailDO> {
    @Update("update order_detail set flag = #{flag} where oid = #{oid}")
    void updateFlagByOid(@Param("oid") Integer oid, @Param("flag") Integer flag);
}