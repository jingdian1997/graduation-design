package com.jd.graduation.mapper;

import com.jd.graduation.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> list();
    User findById(Integer id);
}