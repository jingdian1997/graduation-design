package com.jd.graduation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jd.graduation.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> selectList();
    User selectById(Integer id);
}