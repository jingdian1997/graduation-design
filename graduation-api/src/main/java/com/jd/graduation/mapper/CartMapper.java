package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.CartDO;
import com.jd.graduation.VO.CartVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CartMapper extends BaseMapper<CartDO> {
    List<CartDO> getInIds(@Param("ids") List<Integer> ids, @Param("uid") Integer uid);

    @Select("SELECT cart.*, book.price as price, book.name as name FROM `cart` " +
            "LEFT JOIN book ON cart.bid = book.id " +
            "where cart.uid = #{uid}")
    List<CartVO> selectByUid(@Param("uid") Integer uid);
}