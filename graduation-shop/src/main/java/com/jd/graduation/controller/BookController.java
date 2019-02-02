package com.jd.graduation.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.Impl.BookServiceImpl;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.VO.IndexVO;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@Api(description = "图书查询")
public class BookController extends BaseController{
    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("/list/{page}/{size}")
    public ReturnMap list(@PathVariable("page") int page, @PathVariable("size") int size,
                          @RequestParam(value = "query", required = false) String query,
                          @RequestParam(value = "category", required = false) Integer categoryId) {
        Page<BookVO> books = bookService.selectList(new Page<>(page, size), categoryId, query);
        return ReturnMap.ok(books);
    }

    @GetMapping("/one/{id}")
    public ReturnMap one(@PathVariable("id") Integer id) {
        BookVO book = bookService.selectOne(id);
        return ReturnMap.ok(book);
    }

    @GetMapping("/index")
    public ReturnMap index(){
        int page = 1;
        int size = 4;

        List<IndexVO> map = bookService.index(page, size);
        return ReturnMap.ok(map);
    }
}