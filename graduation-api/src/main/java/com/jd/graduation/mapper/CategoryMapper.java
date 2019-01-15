package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.CategoryDO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CategoryMapper extends BaseMapper<CategoryDO> {
    @Select("select * from category")
    @MapKey("id")
    Map<Integer, CategoryDO> getMap();

    @Select("SELECT id FROM category WHERE FIND_IN_SET(parent_id, #{ids})")
    List<Integer> getByParentIds(@Param("ids") String ids);

    @Select("select * from category where parent_id = 0")
    List<CategoryDO> getTopCategories();

    @Select("select * from category where del = 0")
    @MapKey("id")
    Map<Integer, CategoryDO> getMapNotDel();

    @Select("SELECT id FROM category WHERE FIND_IN_SET(parent_id, #{ids}) and del = 0")
    List<Integer> getByParentIdsNotDel(@Param("ids") String ids);

    @Select("select * from category where del = 0 and parent_id = 0")
    List<CategoryDO> getTopCategoriesNotDel();
}