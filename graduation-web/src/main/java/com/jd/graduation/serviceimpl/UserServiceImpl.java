package com.jd.graduation.serviceimpl;

import com.jd.graduation.entity.User;
import com.jd.graduation.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl extends UserService {
    @Override
    public List<User> selectList(){
        return baseMapper.selectList(null);
    }

    @Override
    public boolean login(String accountName, String accountPassword) {
        //TODO:登录查询
        return false;
    }

    @Override
    public boolean logout(String token) {
        //TODO:登出清除
        return false;
    }

    public boolean register() {
        //TODO:注册
        return false;
    }
}