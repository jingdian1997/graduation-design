package com.jd.graduation.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.DO.BookDO;
import com.jd.graduation.DTO.BookCreateDTO;
import com.jd.graduation.DTO.BookUpdateDTO;
import com.jd.graduation.DTO.BookUpdatePictureDTO;
import com.jd.graduation.Impl.BookPictureServiceImpl;
import com.jd.graduation.Impl.BookServiceImpl;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.service.AuthenticationService;
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
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private BookPictureServiceImpl bookPictureService;

    @PostMapping("/insert")
    public ReturnMap insert(@RequestBody @Valid BookCreateDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(2) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            bookService.insert(dto);
        } catch (Exception e){
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }

    @PostMapping("/update")
    public ReturnMap update(@RequestBody @Valid BookUpdateDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(2) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            bookService.update(dto);
        } catch (Exception e){
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }

    @PostMapping("/updatePicture")
    public ReturnMap updatePicture(@RequestBody @Valid BookUpdatePictureDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(2) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            bookPictureService.updatePicture(dto);
        } catch (Exception e){
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }

    @PostMapping("/pullOff/{id}")
    public ReturnMap pullOff(@PathVariable("id") Integer id, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(2) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        try {
            bookService.pullOff(id);
        } catch (Exception e){
            return ReturnMap.error(e.getMessage());
        }

        return ReturnMap.ok(null);
    }

    @GetMapping("/list/{page}/{size}")
    public ReturnMap list(@PathVariable("page") int page, @PathVariable("size") int size,
                          @RequestParam(value = "query", required = false) String query,
                          @RequestParam(value = "cid", required = false) Integer cid,
                          HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }
        if (!adminDO.getRole().equals(2) && !adminDO.getRole().equals(0)){
            return ReturnMap.notAllowed();
        }

        Page<BookVO> books = null;
        try {
            books = bookService.selectList(new Page<>(page, size), cid, query);
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(books);
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

        BookVO book = null;
        try {
            book = bookService.selectOne(id);
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(book);
    }
}