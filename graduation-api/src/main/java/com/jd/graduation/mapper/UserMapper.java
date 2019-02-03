package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.UserDO;
import com.jd.graduation.VO.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<UserDO> {
    @Select("select u.*, ul.tel, ul.mail from `user` as u " +
            "left join user_login as ul on u.id=ul.id where u.id=#{id}")
    UserVO getUser(@Param("id") Integer id);
}