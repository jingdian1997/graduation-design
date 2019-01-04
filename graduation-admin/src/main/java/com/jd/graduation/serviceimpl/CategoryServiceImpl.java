package com.jd.graduation.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.entity.Category;
import com.jd.graduation.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CategoryServiceImpl")
public class CategoryServiceImpl extends CategoryService {
    private final BookServiceImpl bookService;

    @Autowired
    public CategoryServiceImpl(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    public boolean insert(Category category) {
        if (!checkNameUnique(category.getName())){
            return false;
        }

        baseMapper.insert(category);
        return true;
    }

    public boolean delete(int categoryId) {
        if(bookService.countBookByCategory(categoryId) > 0) {
            return false;
        }
        baseMapper.deleteById(categoryId);

        return true;
    }

    public boolean update(Category category) {
        if (!checkNameUnique(category.getName())){
            return false;
        }

        baseMapper.updateById(category);
        return true;
    }

    private boolean checkNameUnique(String name) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        int count = baseMapper.selectCount(wrapper);

        return count == 0;
    }
}