package com.jd.graduation.Impl;

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
}