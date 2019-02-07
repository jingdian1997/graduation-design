package com.jd.graduation.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.CommentCreateDTO;
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
public class CommentController extends BaseController {
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/makeComment")
    public ReturnMap makeComment(@RequestBody @Valid CommentCreateDTO dto, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            commentService.makeComment(dto, user.getId());
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(null);
    }

    @PostMapping("/deleteComment/{id}")
    public ReturnMap deleteComment(@PathVariable("id") Integer id, HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        try {
            commentService.deleteComment(id, user.getId());
        } catch (Exception e) {
            return ReturnMap.error(e.getMessage());
        }
        return ReturnMap.ok(null);
    }

    @GetMapping("/myComments/{page}/{size}")
    public ReturnMap myComments(@PathVariable("page") Integer page,
                                @PathVariable("size") Integer size,
                                HttpServletRequest request) {
        UserLoginDO user = authenticationService.getUser(getHeaderAuthorization(request));
        if (user == null) {
            return ReturnMap.notLogin();
        }

        Page<CommentVO> list = commentService.getListByUid(user.getId(), new Page<>(page, size));
        return ReturnMap.ok(list);
    }

    @GetMapping("/bookComments/{bid}/{page}/{size}")
    public ReturnMap bookComments(@PathVariable("bid") Integer bid,
                                  @PathVariable("page") Integer page,
                                  @PathVariable("size") Integer size) {
        Page<CommentVO> list = commentService.getListByBid(bid, new Page<>(page, size));
        return ReturnMap.ok(list);
    }
}