package com.jd.graduation.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jd.graduation.entity.Category;
import com.jd.graduation.mapper.CategoryMapper;

import java.util.List;

public abstract class CategoryService extends ServiceImpl<CategoryMapper, Category> {
    public List<Category> selectList() {
        return baseMapper.selectList(null);
    }
}