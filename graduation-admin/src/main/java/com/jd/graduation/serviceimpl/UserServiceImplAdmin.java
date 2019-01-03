package com.jd.graduation.serviceimpl;

import com.jd.graduation.model.AdminConfigVO;
import com.jd.graduation.model.ChangePasswordModel;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserServiceImplAdmin")
public class UserServiceImplAdmin extends UserService {
    private final AuthenticationService authenticationService;
    private final SystemConfigDetailServiceImpl systemConfigDetailService;

    @Autowired
    public UserServiceImplAdmin(AuthenticationService authenticationService,
                                SystemConfigDetailServiceImpl systemConfigDetailService) {
        this.authenticationService = authenticationService;
        this.systemConfigDetailService = systemConfigDetailService;
    }

    @Override
    public String login(String accountName, String accountPassword) {
        AdminConfigVO adminConfigVO = systemConfigDetailService.getAdminConfig();
        if (accountName.equals(adminConfigVO.getAccount())
                && accountPassword.equals(adminConfigVO.getPassword())) {
            //不保存密码
            adminConfigVO.setPassword(null);
            String key = authenticationService.makeToken(accountName);
            authenticationService.set(key, adminConfigVO);

            return key;
        }

        return null;
    }

    @Override
    public boolean logout(String key) {
        authenticationService.delete(key);
        return true;
    }

    public boolean changePwd(ChangePasswordModel model, AdminConfigVO entity) {
        return false;
    }
}