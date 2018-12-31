package com.jd.graduation.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jd.graduation.mapper.UserMapper;
import com.jd.graduation.entity.User;
import com.jd.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> selectList(){
        return userMapper.selectList(null);
    }

    public User selectById(Integer id){
        return userMapper.selectById(id);
    }
}