package com.jd.graduation.service;

import com.jd.graduation.entity.User;

import java.util.List;

public interface UserService {
    List<User> selectList();
    User selectById(Integer id);
}