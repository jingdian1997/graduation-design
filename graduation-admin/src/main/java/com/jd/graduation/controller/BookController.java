package com.jd.graduation.controller;

import com.jd.graduation.model.AdminConfigVO;
import com.jd.graduation.model.request.BookCreateModel;
import com.jd.graduation.model.request.BookUpdateModel;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.serviceimpl.BookServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/book")
@Api(description = "图书管理")
public class BookController extends BaseController {
    private final AuthenticationService authenticationService;
    private final BookServiceImpl bookService;

    @Autowired
    public BookController(AuthenticationService authenticationService, BookServiceImpl bookService) {
        this.authenticationService = authenticationService;
        this.bookService = bookService;
    }

    @PostMapping("/insert")
    public ReturnMap insert(@RequestBody @Valid BookCreateModel model, HttpServletRequest request) {
        AdminConfigVO adminConfigVO = authenticationService.getAdminEntity(getHeaderAuthorization(request));
        if (adminConfigVO == null) {
            return ReturnMap.notLogin();
        }

        bookService.insert(model);
        return ReturnMap.ok(null);
    }

    @PostMapping("/update")
    public ReturnMap update(@RequestBody @Valid BookUpdateModel model, HttpServletRequest request) {
        AdminConfigVO adminConfigVO = authenticationService.getAdminEntity(getHeaderAuthorization(request));
        if (adminConfigVO == null) {
            return ReturnMap.notLogin();
        }

        bookService.update(model);
        return ReturnMap.ok(null);
    }

    @PostMapping("/pullOff/{id}")
    public ReturnMap pullOff(@PathVariable("id") int id, HttpServletRequest request) {
        AdminConfigVO adminConfigVO = authenticationService.getAdminEntity(getHeaderAuthorization(request));
        if (adminConfigVO == null) {
            return ReturnMap.notLogin();
        }

        bookService.pullOff(id);
        return ReturnMap.ok(null);
    }
}