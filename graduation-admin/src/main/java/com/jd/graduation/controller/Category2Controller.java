package com.jd.graduation.controller;

import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.DTO.Category2CreateDTO;
import com.jd.graduation.DTO.Category2UpdateDTO;
import com.jd.graduation.Impl.Category2ServiceImpl;
import com.jd.graduation.VO.Category2VO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category2")
@Api(description = "图书小类管理")
public class Category2Controller extends BaseController{
    @Autowired
    private Category2ServiceImpl category2Service;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/insert")
    public ReturnMap insert(@RequestBody @Valid Category2CreateDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        String message = category2Service.insert(dto);
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

        String message = category2Service.delete(id);
        if (message == null) {
            return ReturnMap.ok(null);
        }

        return ReturnMap.error(message);
    }

    @PostMapping("/update")
    public ReturnMap update(@RequestBody @Valid Category2UpdateDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        String message = category2Service.update(dto);
        if (message == null) {
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

        List<Category2VO> list = category2Service.getList();
        return ReturnMap.ok(list);
    }
}