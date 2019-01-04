package com.jd.graduation.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    private static final String AUTHORIZATION = "Authorization";

    protected String getHeaderAuthorization(HttpServletRequest request) {
        return request.getHeader(AUTHORIZATION);
    }
}