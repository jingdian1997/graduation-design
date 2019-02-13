package com.jd.graduation.Impl;

import com.jd.graduation.DO.UserAddressDO;
import com.jd.graduation.service.UserAddressService;
import org.springframework.stereotype.Service;

@Service("UserAddressServiceImpl")
public class UserAddressServiceImpl extends UserAddressService {
    public UserAddressDO one(Integer id) {
        return baseMapper.selectById(id);
    }
}