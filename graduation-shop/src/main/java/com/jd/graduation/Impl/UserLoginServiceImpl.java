package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.VO.UserVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserLoginServiceImpl")
public class UserLoginServiceImpl extends UserLoginService {
    private final UserServiceImpl userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public UserLoginServiceImpl(AuthenticationService authenticationService, UserServiceImpl userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public String login(String accountName, String accountPassword) {
        QueryWrapper<UserLoginDO> wrapper = new QueryWrapper<>();

        //构造相等条件，注意这里的字段为数据表中的字段，而不是Java实体类
        if (accountName.contains("@")) {
            //是邮箱登录
            wrapper.eq("mail", accountName);
        }else {
            wrapper.eq("tel", accountName);
        }

        UserLoginDO userLoginDO = baseMapper.selectOne(wrapper);
        if (userLoginDO != null && accountPassword.equals(userLoginDO.getPwd())){
            UserVO userVO = userService.getWholeUser(userLoginDO.getId());

            String key = authenticationService.makeToken(userLoginDO.getTel());
            authenticationService.set(key, userVO);

            return key;
        }
        return null;
    }

    @Override
    public void logout(String key) {
        authenticationService.delete(key);
    }
}