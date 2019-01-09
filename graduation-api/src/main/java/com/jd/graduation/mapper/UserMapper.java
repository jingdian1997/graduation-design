package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.UserDO;
import com.jd.graduation.VO.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<UserDO> {
    @Select("select u.*, ul.tel, ul.mail, ul.pwd from `user` u LEFT JOIN user_login ul " +
            "on u.id = ul.id")
    List<UserVO> list();

    @Select("select u.*, ul.tel, ul.mail, ul.pwd from `user` u " +
            "LEFT JOIN user_login ul on u.id = ul.id WHERE u.id = #{id}")
    UserVO findById(@Param("id") Integer id);
}