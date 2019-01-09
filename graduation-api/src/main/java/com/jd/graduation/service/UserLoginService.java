package com.jd.graduation.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.mapper.UserLoginMapper;

public abstract class UserLoginService extends ServiceImpl<UserLoginMapper, UserLoginDO> {
    public abstract String login(String accountName, String accountPassword);
    public abstract void logout(String key);
}