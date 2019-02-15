package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.UserFocusDO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.VO.FocusVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserFocusMapper extends BaseMapper<UserFocusDO> {
    @Select("SELECT uf.*, book.price as price, book.name as name FROM `user_focus` as uf " +
            "LEFT JOIN book ON uf.bid = book.id where uf.uid = #{uid}")
    List<FocusVO> selectByUid(@Param("uid") Integer uid);

    @Select("SELECT b.*, bp.picture FROM `user_focus` as uf LEFT JOIN book as b ON uf.bid=b.id " +
            "LEFT JOIN book_picture as bp ON bp.id=uf.bid WHERE b.del=0 " +
            "GROUP BY uf.bid ORDER BY COUNT(1) DESC LIMIT #{size}")
    public List<BookVO> getMostFocused(@Param("size") int size);
}