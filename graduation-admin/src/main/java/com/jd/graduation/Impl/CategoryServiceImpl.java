package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.CategoryDO;
import com.jd.graduation.DTO.CategoryCreateDTO;
import com.jd.graduation.DTO.CategoryUpdateDTO;
import com.jd.graduation.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CategoryServiceImpl")
public class CategoryServiceImpl extends CategoryService {
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private Category2ServiceImpl category2Service;

    public String insert(CategoryCreateDTO dto) {
        if (!checkNameUnique(dto.getName())){
            return "已经存在同名的类别";
        }

        CategoryDO category = new CategoryDO();
        category.setName(dto.getName());
        category.setDel(false);

        baseMapper.insert(category);
        return null;
    }

    public String delete(Integer cid) {
        if(bookService.countBookByCategoryAndDel(cid) > 0) {
            return "删除失败，请检查该分类下是否有图书";
        }
        if (category2Service.countByCid(cid) > 0) {
            return "删除失败，请检查该分类下是否有小类";
        }

        CategoryDO categoryDO = baseMapper.selectById(cid);
        categoryDO.setDel(true);

        baseMapper.updateById(categoryDO);
        return null;
    }

    public String update(CategoryUpdateDTO dto) {
        CategoryDO categoryDO = baseMapper.selectById(dto.getId());
        if (!checkNameUnique(dto.getName())){
            return "已经存在同名的类别，或者你没有更改类名";
        }

        categoryDO.setName(dto.getName());
        baseMapper.updateById(categoryDO);
        return null;
    }

    public List<CategoryDO> getList() {
        return baseMapper.selectList(null);
    }

    private boolean checkNameUnique(String name) {
        QueryWrapper<CategoryDO> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        wrapper.eq("del", false);

        int count = baseMapper.selectCount(wrapper);
        return count == 0;
    }
}