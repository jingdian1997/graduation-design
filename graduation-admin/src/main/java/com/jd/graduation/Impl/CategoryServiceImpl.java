package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.CategoryDO;
import com.jd.graduation.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CategoryServiceImpl")
public class CategoryServiceImpl extends CategoryService {
    @Autowired
    private BookServiceImpl bookService;

//    public boolean insert(Category category) {
//        if (!checkNameUnique(category.getName())){
//            return false;
//        }
//
//        baseMapper.insert(category);
//        return true;
//    }
//
//    public boolean delete(int categoryId) {
//        if(bookService.countBookByCategory(categoryId) > 0) {
//            return false;
//        }
//        baseMapper.deleteById(categoryId);
//
//        return true;
//    }
//
//    public boolean update(Category category) {
//        if (!checkNameUnique(category.getName())){
//            return false;
//        }
//
//        baseMapper.updateById(category);
//        return true;
//    }

    private boolean checkNameUnique(String name) {
        QueryWrapper<CategoryDO> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        wrapper.eq("del", false);

        int count = baseMapper.selectCount(wrapper);
        return count == 0;
    }
}