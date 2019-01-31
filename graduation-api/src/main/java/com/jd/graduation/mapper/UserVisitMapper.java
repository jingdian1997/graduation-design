package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.UserVisitDO;
import com.jd.graduation.VO.VisitVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserVisitMapper extends BaseMapper<UserVisitDO> {
    @Select("SELECT uv.*, book.price as price, book.name as name FROM `user_visit` as uv " +
            "LEFT JOIN book ON uv.bid = book.id where uv.uid = #{uid} and uv.del=0")
    List<VisitVO> selectByUid(@Param("uid") Integer uid);

    @Update("update user_visit set del=1 where uid=#{uid} and FIND_IN_SET(id, #{ids})")
    void updateDel(@Param("uid") Integer uid, @Param("ids") String ids);
}