package com.jd.graduation.controller;

import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.DO.CategoryDO;
import com.jd.graduation.DTO.CategoryCreateDTO;
import com.jd.graduation.DTO.CategoryUpdateDTO;
import com.jd.graduation.Impl.CategoryServiceImpl;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
@Api(description = "图书大类管理")
public class CategoryController extends BaseController{
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/insert")
    public ReturnMap insert(@RequestBody @Valid CategoryCreateDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(2) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            categoryService.insert(dto);
            return ReturnMap.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMap.error(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ReturnMap delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(2) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            categoryService.delete(id);
            return ReturnMap.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMap.error(e.getMessage());
        }
    }

    @PostMapping("/active/{id}")
    public ReturnMap active(@PathVariable("id") Integer id, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(2) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            categoryService.active(id);
            return ReturnMap.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMap.error(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ReturnMap update(@RequestBody @Valid CategoryUpdateDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(2) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            categoryService.update(dto);
            return ReturnMap.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnMap.error(e.getMessage());
        }
    }

    @GetMapping("/list/{cid}")
    public ReturnMap list(@PathVariable("cid") Integer cid, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(2) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        List<CategoryDO> list = categoryService.getSonCategories(cid);
        return ReturnMap.ok(list);
    }

    @GetMapping("/one/{id}")
    public ReturnMap one(@PathVariable("id") Integer id, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(2) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        CategoryDO one = categoryService.getOne(id);
        return ReturnMap.ok(one);
    }
}