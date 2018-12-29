package com.jd.graduation.service;

import com.jd.graduation.entity.User;

import java.util.List;

public interface UserService {
    public List<User> list();
    public User findById(Integer id);
}