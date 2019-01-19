package com.jd.graduation.controller;

import com.jd.graduation.Impl.CommentServiceImpl;
import com.jd.graduation.service.AuthenticationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
@Api(description = "评论相关")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;
    @Autowired
    private AuthenticationService authenticationService;
}