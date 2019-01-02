package com.jd.graduation.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.entity.User;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceImpl")
public class UserServiceImpl extends UserService {
    private final AuthenticationService authenticationService;

    @Autowired
    public UserServiceImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public List<User> selectList(){
        return baseMapper.selectList(null);
    }

    @Override
    public String login(String accountName, String accountPassword) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //构造想相等条件，注意这里的字段为数据表中的字段，而不是Java实体类
        wrapper.eq("account_name", accountName);

        User user = baseMapper.selectOne(wrapper);
        if (user != null && accountPassword.equals(user.getAccountPassword())){
            //不保存密码
            user.setAccountPassword(null);

            String key = authenticationService.makeToken(user.getAccountName());
            authenticationService.set(key, user);

            return key;
        }

        return null;
    }

    @Override
    public boolean logout(String token) {
        return true;
    }

    public boolean register() {
        return true;
    }
}