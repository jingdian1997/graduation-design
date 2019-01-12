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

        String message = categoryService.insert(dto);
        if (message == null){
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }

    @PostMapping("/delete/{id}")
    public ReturnMap delete(@PathVariable("id") Integer id, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        String message = categoryService.delete(id);
        if (message == null){
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }

    @PostMapping("/update")
    public ReturnMap update(@RequestBody @Valid CategoryUpdateDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        String message = categoryService.update(dto);
        if (message == null){
            return ReturnMap.ok(null);
        }
        return ReturnMap.error(message);
    }

    @GetMapping("/list")
    public ReturnMap list(HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        List<CategoryDO> list = categoryService.getList();
        return ReturnMap.ok(list);
    }
}