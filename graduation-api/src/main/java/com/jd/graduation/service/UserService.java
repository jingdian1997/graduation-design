package com.jd.graduation.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jd.graduation.entity.User;
import com.jd.graduation.mapper.UserMapper;

import java.util.List;

public abstract class UserService extends ServiceImpl<UserMapper, User> {
    public abstract List<User> selectList();
    public abstract String login(String accountName, String accountPassword);
    public abstract boolean logout(String token);
}