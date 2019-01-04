package com.jd.graduation.controller;

import com.jd.graduation.entity.Category;
import com.jd.graduation.model.AdminConfigVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.serviceimpl.CategoryServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/category")
@Api(description = "图书大类管理")
public class CategoryController extends BaseController{
    private final CategoryServiceImpl categoryService;
    private final AuthenticationService authenticationService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService, AuthenticationService authenticationService) {
        this.categoryService = categoryService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/insert")
    public ReturnMap insert(@RequestBody Category category, HttpServletRequest request) {
        AdminConfigVO adminConfigVO = authenticationService.getAdminEntity(getHeaderAuthorization(request));
        if (adminConfigVO == null) {
            return ReturnMap.notLogin();
        }

        boolean result = categoryService.insert(category);
        if (result){
            return ReturnMap.ok(null);
        }
        return ReturnMap.error("修改失败，请检查是否类别重名");
    }

    @PostMapping("/delete/{id}")
    public ReturnMap delete(@PathVariable("id") int id, HttpServletRequest request) {
        AdminConfigVO adminConfigVO = authenticationService.getAdminEntity(getHeaderAuthorization(request));
        if (adminConfigVO == null) {
            return ReturnMap.notLogin();
        }

        boolean res = categoryService.delete(id);
        if (res) {
            return ReturnMap.ok(null);
        }
        return ReturnMap.error("删除失败，请检查该分类下是否有图书");
    }

    @PostMapping("/update")
    public ReturnMap update(@RequestBody Category category, HttpServletRequest request) {
        AdminConfigVO adminConfigVO = authenticationService.getAdminEntity(getHeaderAuthorization(request));
        if (adminConfigVO == null) {
            return ReturnMap.notLogin();
        }

        boolean res = categoryService.update(category);
        if (res) {
            return ReturnMap.ok(null);
        }
        return ReturnMap.error("修改失败，请检查类别重名或者没有任何更改");
    }

    @GetMapping("/list")
    public ReturnMap list() {
        List<Category> list = categoryService.selectList();
        return ReturnMap.ok(list);
    }
}