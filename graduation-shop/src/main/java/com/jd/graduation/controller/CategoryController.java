package com.jd.graduation.controller;

import com.jd.graduation.DO.CategoryDO;
import com.jd.graduation.Impl.CategoryServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@Api(description = "分类查询")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/list/{cid}")
    public ReturnMap list(@PathVariable("cid") Integer cid) {
        List<CategoryDO> list;
        try {
            list = categoryService.getSonCategories(cid);
        } catch (Exception e){
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(list);
    }
}