package com.jd.graduation.Impl;

import com.jd.graduation.VO.UserVO;
import com.jd.graduation.service.UserService;
import org.springframework.stereotype.Service;

@Service("UserServiceImpl")
public class UserServiceImpl extends UserService {
    public UserVO getWholeUser(Integer id) {
        return baseMapper.findById(id);
    }
}