package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.CategoryDO;
import com.jd.graduation.service.CategoryService;
import com.jd.graduation.util.MyStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("CategoryServiceImpl")
public class CategoryServiceImpl extends CategoryService {
    public Map<Integer, CategoryDO> getCategoryMap(){
        return baseMapper.getMapNotDel();
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

            List<Integer> now = baseMapper.getByParentIdsNotDel(str);
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

    public List<CategoryDO> getTopCategories(){
        return baseMapper.getTopCategoriesNotDel();
    }

    /**
     * @param cid 父类别id
     * @return 获取该类别下的小类别
     */
    public List<CategoryDO> getSonCategories(Integer cid) {
        QueryWrapper<CategoryDO> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", cid);
        wrapper.eq("del", 0);

        return baseMapper.selectList(wrapper);
    }
}