package com.jd.graduation.serviceimpl;

import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserServiceImplAdmin")
public class UserServiceImplAdmin extends UserService {
    private final AuthenticationService authenticationService;

    @Autowired
    public UserServiceImplAdmin(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public String login(String accountName, String accountPassword) {
        return null;
    }

    @Override
    public boolean logout(String key) {
        authenticationService.delete(key);
        return true;
    }
}