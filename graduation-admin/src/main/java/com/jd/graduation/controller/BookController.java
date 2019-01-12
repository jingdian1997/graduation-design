package com.jd.graduation.controller;

import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.model.request.BookCreateModel;
import com.jd.graduation.model.request.BookUpdateModel;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.Impl.BookServiceImpl;
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
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

//        bookService.insert(model);
        return ReturnMap.ok(null);
    }

    @PostMapping("/update")
    public ReturnMap update(@RequestBody @Valid BookUpdateModel model, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

//        bookService.update(model);
        return ReturnMap.ok(null);
    }

    @PostMapping("/pullOff/{id}")
    public ReturnMap pullOff(@PathVariable("id") int id, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

//        bookService.pullOff(id);
        return ReturnMap.ok(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ReturnMap list(@PathVariable("page") int page, @PathVariable("size") int size,
                          @RequestParam(value = "query", required = false) String query,
                          @RequestParam(value = "category", required = false) Integer categoryId,
                          HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

//        Page<BookCategoryVO> books = bookService.selectList(new Page<>(page, size), categoryId, query);
        return ReturnMap.ok(null);
    }

    @GetMapping("/one/{id}")
    public ReturnMap one(@PathVariable("id") Integer id, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

//        BookCategoryVO book = bookService.selectOne(id);
        return ReturnMap.ok(null);
    }
}