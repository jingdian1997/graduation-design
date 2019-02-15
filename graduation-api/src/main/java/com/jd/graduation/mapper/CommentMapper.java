package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.CommentDO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.VO.CommentVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper extends BaseMapper<CommentDO> {
    @Select("SELECT c.*, u.nickname, b.`name` FROM " +
            "(`comment` AS c LEFT JOIN `user` AS u ON c.uid = u.id) " +
            "LEFT JOIN book AS b ON c.bid = b.id WHERE c.uid = #{uid}")
    List<CommentVO> getByUid(@Param("uid") Integer uid, Page<CommentVO> page);

    @Select("SELECT c.*, u.nickname, b.`name` FROM " +
            "(`comment` AS c LEFT JOIN `user` AS u ON c.uid = u.id) " +
            "LEFT JOIN book AS b ON c.bid = b.id WHERE c.bid = #{bid}")
    List<CommentVO> getByBid(@Param("bid") Integer bid, Page<CommentVO> page);

    @Select("SELECT c.*, u.nickname, b.`name` FROM " +
            "(`comment` AS c LEFT JOIN `user` AS u ON c.uid = u.id) " +
            "LEFT JOIN book AS b ON c.bid = b.id")
    List<CommentVO> get(Page<CommentVO> page);

    @Select("SELECT b.*, bp.picture FROM `comment` as c LEFT JOIN book as b ON c.bid=b.id " +
            "LEFT JOIN book_picture as bp ON bp.id=c.bid WHERE b.del=0 " +
            "GROUP BY c.bid ORDER BY AVG(c.score) DESC LIMIT #{size}")
    List<BookVO> getHighestScore(@Param("size") int size);
}