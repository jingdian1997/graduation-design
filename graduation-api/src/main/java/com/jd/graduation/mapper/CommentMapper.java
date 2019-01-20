package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.CommentDO;
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
}