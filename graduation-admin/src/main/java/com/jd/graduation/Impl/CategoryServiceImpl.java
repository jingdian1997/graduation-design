package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.CategoryDO;
import com.jd.graduation.DTO.CategoryCreateDTO;
import com.jd.graduation.DTO.CategoryUpdateDTO;
import com.jd.graduation.service.CategoryService;
import com.jd.graduation.util.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("CategoryServiceImpl")
public class CategoryServiceImpl extends CategoryService {
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private SysConfigServiceImpl sysConfigService;

    @Transactional(rollbackFor = Exception.class)
    public void insert(CategoryCreateDTO dto) throws Exception {
        if (!checkNameUnique(dto.getName())){
            throw new Exception("已经存在同名的类别");
        }

        CategoryDO category = new CategoryDO();
        category.setName(dto.getName());
        category.setParentId(dto.getParentId());
        category.setDel(false);

        baseMapper.insert(category);
        sysConfigService.raiseCategoryVersion();
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer cid) throws Exception {
        if(bookService.countBookByCategoryAndDel(cid) > 0) {
            throw new Exception("删除失败，请检查该分类下是否有图书");
        }
        if (countSonCategory(cid) > 0) {
            throw new Exception("删除失败，请检查该分类下是否有小类");
        }

        CategoryDO categoryDO = baseMapper.selectById(cid);
        categoryDO.setDel(true);

        baseMapper.updateById(categoryDO);
        sysConfigService.raiseCategoryVersion();
    }

    @Transactional(rollbackFor = Exception.class)
    public void active(Integer id) throws Exception{
        CategoryDO categoryDO = baseMapper.selectById(id);
        categoryDO.setDel(false);

        baseMapper.updateById(categoryDO);
        sysConfigService.raiseCategoryVersion();
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(CategoryUpdateDTO dto) throws Exception {
        CategoryDO categoryDO = baseMapper.selectById(dto.getId());
        if (!checkNameUnique(dto.getName())){
            throw new Exception("已经存在同名的类别，或者你没有更改类名");
        }

        categoryDO.setName(dto.getName());
        baseMapper.updateById(categoryDO);
        sysConfigService.raiseCategoryVersion();
    }

    public List<CategoryDO> getList() {
        return baseMapper.selectList(null);
    }

    public Map<Integer, CategoryDO> getCategoryMap(){
        return baseMapper.getMap();
    }

    private boolean checkNameUnique(String name) {
        QueryWrapper<CategoryDO> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);

        int count = baseMapper.selectCount(wrapper);
        return count == 0;
    }

    private int countSonCategory(Integer parentId) {
        QueryWrapper<CategoryDO> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        wrapper.eq("del", false);

        return baseMapper.selectCount(wrapper);
    }

    /**
     * @param cid 父类别id
     * @return 父类别下所有子类别
     */
    public List<Integer> getAllCategoryIds(Integer cid) {
        List<Integer> list = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();

        list.add(cid);
        pre.add(cid);

        while (true) {
            String str = MyStringUtils.listToString(pre);

            List<Integer> now = baseMapper.getByParentIds(str);
            if (now.size() == 0){
                break;
            }

            pre.clear();
            pre.addAll(now);
            list.addAll(pre);
        }

        return list;
    }

    /**
     * @param cid 子类别id
     * @return 从子类别到最高层父类别的路径
     */
    public List<CategoryDO> getAllCategories(Integer cid) {
        List<CategoryDO> list = new ArrayList<>();
        Map<Integer, CategoryDO> map = getCategoryMap();

        while (cid > 0) {
            CategoryDO categoryDO = map.get(cid);
            list.add(categoryDO);
            cid = categoryDO.getParentId();
        }

        return list;
    }

    /**
     * @param cid 父类别id
     * @return 获取该类别下的小类别
     */
    public List<CategoryDO> getSonCategories(Integer cid) {
        QueryWrapper<CategoryDO> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", cid);

        return baseMapper.selectList(wrapper);
    }
}