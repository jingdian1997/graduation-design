package com.jd.graduation.serviceimpl;

import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.model.AdminConfigVO;
import com.jd.graduation.service.AdminService;
import com.jd.graduation.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminServiceImpl")
public class AdminServiceImpl extends AdminService {
    private final AuthenticationService authenticationService;

    @Autowired
    public AdminServiceImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public String login(String account, String pwd) {
        AdminDO adminDO = baseMapper.selectById(account);

        if (adminDO != null && pwd.equals(adminDO.getId())) {
            String key = authenticationService.makeToken(account);
            authenticationService.set(key, adminDO);

            return key;
        }
        return null;
    }

    public void logout(String key) {
        authenticationService.delete(key);
    }
}