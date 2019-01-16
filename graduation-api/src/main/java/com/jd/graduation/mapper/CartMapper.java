package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.CartDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper extends BaseMapper<CartDO> {
    List<CartDO> getInIds(@Param("ids") List<Integer> ids, @Param("uid") Integer uid);
}