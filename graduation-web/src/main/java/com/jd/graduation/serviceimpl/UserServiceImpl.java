package com.jd.graduation.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.entity.User;
import com.jd.graduation.model.request.ChangePasswordModel;
import com.jd.graduation.model.request.UserChangeInfoModel;
import com.jd.graduation.model.request.UserCreateModel;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
public class UserServiceImpl extends UserService {
    private final AuthenticationService authenticationService;

    @Autowired
    public UserServiceImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public String login(String accountName, String accountPassword) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //构造相等条件，注意这里的字段为数据表中的字段，而不是Java实体类
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
    public void logout(String key) {
        authenticationService.delete(key);
    }

    public boolean register(UserCreateModel model) {
        if (!model.getAccountPassword().equals(model.getAccountPasswordAgain())){
            return false;
        }

        User user = new User();
        user.setAccountName(model.getAccountName());
        user.setAccountPassword(model.getAccountPassword());
        user.setName(model.getName());
        if ("女".equals(model.getSex())) {
            user.setSex("女");
        } else {
            user.setSex("男");
        }
        user.setIdCard(model.getIdCard());
        user.setTelephone(model.getTelephone());
        user.setMail(model.getEmail());

        int result = baseMapper.insert(user);

        return true;
    }

    public boolean changePwd(ChangePasswordModel model, int id) {
        User user = baseMapper.selectById(id);

        if (user.getAccountPassword().equals(model.getPrePassword())
                && model.getNewPassword().equals(model.getNewPasswordAgain())) {
            user.setAccountPassword(model.getNewPassword());

            int result = baseMapper.updateById(user);
            return true;
        }

        return false;
    }

    public boolean changeInfo(UserChangeInfoModel model, int id) {
        User user = baseMapper.selectById(id);
        user.setMail(model.getMail());
        user.setTelephone(model.getTelephone());

        int result = baseMapper.updateById(user);
        return true;
    }
}