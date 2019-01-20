package com.jd.graduation.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.DTO.CommentReplyDTO;
import com.jd.graduation.Impl.CommentServiceImpl;
import com.jd.graduation.VO.CommentVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
@Api(description = "评论相关")
public class CommentController extends BaseController{
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/bookComments/{bid}/{page}/{size}")
    public ReturnMap bookComments(@PathVariable("bid") Integer bid,
                                  @PathVariable("page") Integer page,
                                  @PathVariable("size") Integer size,
                                  HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        Page<CommentVO> list = commentService.getListByBid(bid, new Page<>(page, size));
        return ReturnMap.ok(list);
    }

    @GetMapping("/comments/{page}/{size}")
    public ReturnMap comments(@PathVariable("page") Integer page,
                              @PathVariable("size") Integer size,
                              HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        Page<CommentVO> list = commentService.getList(new Page<>(page, size));
        return ReturnMap.ok(list);
    }

    @PostMapping("/replyComment")
    public ReturnMap replyComment(@RequestBody @Valid CommentReplyDTO dto, HttpServletRequest request) {
        AdminDO adminDO = authenticationService.getAdmin(getHeaderAuthorization(request));
        if (adminDO == null) {
            return ReturnMap.notLogin();
        }

        try {
            commentService.replyComment(dto);
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(null);
    }
}