package com.jd.graduation.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.entity.User;
import com.jd.graduation.model.response.BookCategoryVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.serviceimpl.BookServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/book")
@Api(description = "图书查询")
public class BookController extends BaseController{
    private final BookServiceImpl bookService;
    private final AuthenticationService authenticationService;

    @Autowired
    public BookController(BookServiceImpl bookService, AuthenticationService authenticationService) {
        this.bookService = bookService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/list/{page}/{size}")
    public ReturnMap list(@PathVariable("page") int page, @PathVariable("size") int size,
                          @RequestParam(value = "query", required = false) String query,
                          @RequestParam(value = "category", required = false) Integer categoryId) {
//        User user = authenticationService.getUserEntity(getHeaderAuthorization(request));
//        if (user == null) {
//            return ReturnMap.notLogin();
//        }
        Page<BookCategoryVO> books = bookService.selectList(new Page<>(page, size), categoryId, query);
        return ReturnMap.ok(books);
    }

    @GetMapping("/one/{id}")
    public ReturnMap one(@PathVariable("id") Integer id) {
        BookCategoryVO book = bookService.selectOne(id);
        return ReturnMap.ok(book);
    }
}