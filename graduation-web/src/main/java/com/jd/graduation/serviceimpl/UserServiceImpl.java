package com.jd.graduation.serviceimpl;

import com.jd.graduation.mapper.UserMapper;
import com.jd.graduation.entity.User;
import com.jd.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> list(){
        return userMapper.list();
    }

    public User findById(Integer id){
        return userMapper.findById(id);
    }
}