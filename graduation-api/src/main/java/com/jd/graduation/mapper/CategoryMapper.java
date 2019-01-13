package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.CategoryDO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface CategoryMapper extends BaseMapper<CategoryDO> {
    @Select("select * from category")
    @MapKey("id")
    Map<Integer, CategoryDO> getMap();
}