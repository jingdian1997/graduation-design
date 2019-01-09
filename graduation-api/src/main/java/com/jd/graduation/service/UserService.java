package com.jd.graduation.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jd.graduation.DO.UserDO;
import com.jd.graduation.mapper.UserMapper;

public abstract class UserService extends ServiceImpl<UserMapper, UserDO> {
    public abstract String login(String accountName, String accountPassword);
    public abstract void logout(String key);
}