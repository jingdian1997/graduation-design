package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.Category2DO;
import com.jd.graduation.DTO.Category2CreateDTO;
import com.jd.graduation.DTO.Category2UpdateDTO;
import com.jd.graduation.VO.Category2VO;
import com.jd.graduation.service.Category2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Category2ServiceImpl")
public class Category2ServiceImpl extends Category2Service {
    @Autowired
    private BookServiceImpl bookService;

    public String insert(Category2CreateDTO dto) {
        if (!checkNameUnique(dto.getName(), dto.getCid())){
            return "大类别下已经存在同名的小类别";
        }

        Category2DO category = new Category2DO();
        category.setName(dto.getName());
        category.setCid(dto.getCid());
        category.setDel(false);

        baseMapper.insert(category);
        return null;
    }

    public String delete(Integer c2id) {
        if(bookService.countBookByCategory2AndDel(c2id) > 0) {
            return "删除失败，请检查该分类下是否有图书";
        }

        Category2DO category2DO = baseMapper.selectById(c2id);
        category2DO.setDel(true);

        baseMapper.updateById(category2DO);
        return null;
    }

    public String update(Category2UpdateDTO dto) {
        Category2DO category2DO = baseMapper.selectById(dto.getId());
        if (!checkNameUnique(dto.getName(), category2DO.getCid())){
            return "大类别下已经存在同名的小类别，或者你没有更改类名";
        }

        category2DO.setName(dto.getName());
        baseMapper.updateById(category2DO);
        return null;
    }

    public List<Category2VO> getList() {
        return baseMapper.getList();
    }

    private boolean checkNameUnique(String name, Integer cid) {
        QueryWrapper<Category2DO> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        wrapper.eq("cid", cid);
        wrapper.eq("del", false);

        int count = baseMapper.selectCount(wrapper);
        return count == 0;
    }

    public int countByCid(Integer cid) {
        QueryWrapper<Category2DO> wrapper = new QueryWrapper<>();
        wrapper.eq("cid", cid);
        wrapper.eq("del", false);

        return baseMapper.selectCount(wrapper);
    }
}