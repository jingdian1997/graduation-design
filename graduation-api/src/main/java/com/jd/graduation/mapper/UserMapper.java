package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.UserDO;

public interface UserMapper extends BaseMapper<UserDO> {
//    @Select("select * FROM(`user` AS u LEFT JOIN user_login AS ul ON u.id=ul.id) " +
//            "LEFT JOIN user_purse as up ON u.id = up.id")
//    List<UserVO> list();
//
//    @Select("select * FROM(`user` AS u LEFT JOIN user_login AS ul ON u.id=ul.id) " +
//            "LEFT JOIN user_purse as up ON u.id = up.id WHERE u.id = #{id}")
//    UserVO findById(@Param("id") Integer id);
}