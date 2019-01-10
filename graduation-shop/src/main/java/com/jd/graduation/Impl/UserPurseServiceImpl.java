package com.jd.graduation.Impl;

import com.jd.graduation.DO.UserPurseDO;
import com.jd.graduation.service.UserPurseService;
import org.springframework.stereotype.Service;

@Service("UserPurseServiceImpl")
public class UserPurseServiceImpl extends UserPurseService {
    public int insert(UserPurseDO userPurseDO) {
        return baseMapper.insert(userPurseDO);
    }
}