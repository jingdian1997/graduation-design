package com.jd.graduation.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jd.graduation.entity.User;
import com.jd.graduation.mapper.UserMapper;

public abstract class UserService extends ServiceImpl<UserMapper, User> {
    public abstract String login(String accountName, String accountPassword);
    public abstract boolean logout(String key);
}