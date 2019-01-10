package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.UserDO;
import com.jd.graduation.VO.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<UserDO> {
    @Select("select * FROM(`user` AS u LEFT JOIN user_login AS ul ON u.id=ul.id) " +
            "LEFT JOIN user_purse as up ON u.id = up.id WHERE u.id=1")
    List<UserVO> list();

    @Select("select * FROM(`user` AS u LEFT JOIN user_login AS ul ON u.id=ul.id) " +
            "LEFT JOIN user_purse as up ON u.id = up.id WHERE u.id = #{id}")
    UserVO findById(@Param("id") Integer id);
}