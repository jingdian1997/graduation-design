package com.jd.graduation.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    protected String getHeaderAuthorization(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }
}