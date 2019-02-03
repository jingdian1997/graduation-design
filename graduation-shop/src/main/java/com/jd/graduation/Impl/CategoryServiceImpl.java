package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.CategoryDO;
import com.jd.graduation.VO.CategoryVO;
import com.jd.graduation.service.CategoryService;
import com.jd.graduation.util.MyStringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("CategoryServiceImpl")
public class CategoryServiceImpl extends CategoryService {
    private Map<Integer, CategoryDO> map;
    private CategoryVO root = null;

    public Map<Integer, CategoryDO> getCategoryMap(){
        if (map == null){
            map = baseMapper.getMapNotDel();
        }
        return map;
    }

    public CategoryVO categoryTreeSet() {
        if (root == null) {
            List<CategoryVO> list = baseMapper.getTopCategoryVONotDel();
            root = new CategoryVO();
            root.setId(0);
            createTree(list, root);
        }

        return root;
    }

    /**
     *  递归建树
     */
    private void createTree(List<CategoryVO> list, CategoryVO present) {
        Integer parentId = present.getId();
        List<CategoryVO> categoryVOS = new ArrayList<>();

        for (CategoryVO one : list) {
            if (one.getParentId().equals(parentId)) {
                createTree(list, one);
                categoryVOS.add(one);
            }
        }

        present.setChildren(categoryVOS);
    }

    /**
     * @param cid 父类的id
     * @return 对应的对象
     */
    public CategoryVO findById(Integer cid) {
        if (root == null) {
            categoryTreeSet();
        }
        return find(cid, root);
    }

    private CategoryVO find(Integer cid, CategoryVO now) {
        if (now.getId().equals(cid)){
            return now;
        }
        List<CategoryVO> childList = now.getChildren();
        if (childList == null) {
            return null;
        }

        int size = childList.size();
        if (size == 0) {
            return null;
        }

        for (int i = 0; i < size; ++i) {
            CategoryVO junior = childList.get(i);
            CategoryVO result = find(cid, junior);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    /* 返回当前节点的晚辈集合 */
    public List<CategoryVO> getJuniors(CategoryVO now) {
        List<CategoryVO> juniorList = new ArrayList<>();
        List<CategoryVO> childList = now.getChildren();

        if (childList == null) {
            return juniorList;
        } else {
            int childNumber = childList.size();
            for (int i = 0; i < childNumber; ++i) {
                CategoryVO junior = childList.get(i);
                juniorList.add(junior);
                juniorList.addAll(getJuniors(junior));
            }

            return juniorList;
        }
    }

    /**
     * @param cid 父类别id
     * @return 父类别下所有子类别，不区分层级
     */
    public List<Integer> getAllCategoryIds(Integer cid) {
        CategoryVO target = findById(cid);
        List<CategoryVO> list = getJuniors(target);

        List<Integer> data = new ArrayList<>();
        for (CategoryVO one : list) {
            data.add(one.getId());
        }
        // 算上自己
        data.add(cid);
        return data;
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