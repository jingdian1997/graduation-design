package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.UserAddressDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserAddressMapper extends BaseMapper<UserAddressDO> {
    @Update("update user_address set defaulting = 0 where uid = #{uid}")
    void setNotDefault(@Param("uid") Integer uid);
}