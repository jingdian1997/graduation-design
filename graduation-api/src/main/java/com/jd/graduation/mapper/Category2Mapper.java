package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.Category2DO;
import com.jd.graduation.VO.Category2VO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Category2Mapper extends BaseMapper<Category2DO> {
    @Select("select c2.*, c.name as cname FROM category2 as c2 LEFT JOIN category as c ON c2.cid = c.id")
    List<Category2VO> getList();
}