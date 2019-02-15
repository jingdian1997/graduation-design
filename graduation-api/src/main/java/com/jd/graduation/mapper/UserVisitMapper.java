package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.UserVisitDO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.VO.VisitVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserVisitMapper extends BaseMapper<UserVisitDO> {
    @Select("SELECT uv.*, book.price as price, book.name as name FROM `user_visit` as uv " +
            "LEFT JOIN book ON uv.bid = book.id where uv.uid = #{uid} and uv.del=0")
    List<VisitVO> selectByUid(@Param("uid") Integer uid);

    @Update("update user_visit set del=1 where uid=#{uid}")
    void updateDel(@Param("uid") Integer uid);

    @Select("SELECT b.*, bp.picture FROM `user_visit` as uv LEFT JOIN book as b ON uv.bid=b.id " +
            "LEFT JOIN book_picture as bp ON bp.id=uv.bid WHERE b.del=0 " +
            "GROUP BY uv.bid ORDER BY COUNT(1) DESC LIMIT #{size}")
    List<BookVO> getMostVisited(@Param("size") Integer size);
}