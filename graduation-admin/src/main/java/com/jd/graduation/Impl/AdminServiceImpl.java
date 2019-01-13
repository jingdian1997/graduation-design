package com.jd.graduation.Impl;

import com.jd.graduation.DO.AdminDO;
import com.jd.graduation.DTO.AdminChangeInfoDTO;
import com.jd.graduation.DTO.AdminCreateDTO;
import com.jd.graduation.DTO.ChangePasswordDTO;
import com.jd.graduation.service.AdminService;
import com.jd.graduation.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AdminServiceImpl")
public class AdminServiceImpl extends AdminService {
    @Autowired
    private AuthenticationService authenticationService;

    public String login(String account, String pwd) {
        AdminDO adminDO = baseMapper.selectById(account);

        if (adminDO != null && pwd.equals(adminDO.getPwd())) {
            // 不存密码，邮件，电话
            adminDO.setPwd(null);
            adminDO.setTel(null);
            adminDO.setMail(null);

            String key = authenticationService.makeToken(account);
            authenticationService.set(key, adminDO);

            return key;
        }
        return null;
    }

    public void logout(String key) {
        authenticationService.delete(key);
    }

    public void create(AdminCreateDTO dto){
        AdminDO adminDO = new AdminDO();

        adminDO.setId(dto.getId());
        adminDO.setPwd(dto.getPwd());
        adminDO.setName(dto.getName());
        adminDO.setRole(dto.getRole());

        baseMapper.insert(adminDO);
    }

    public String changePwd(String id, ChangePasswordDTO dto) {
        AdminDO adminDO = baseMapper.selectById(id);
        String prePwd = adminDO.getPwd();

        if (!dto.getPrePassword().equals(prePwd)){
            return "原密码错误";
        }
        if (prePwd.equals(dto.getNewPassword())){
            return "新密码不能与原密码相同";
        }
        if (!dto.getNewPassword().equals(dto.getNewPasswordAgain())) {
            return "两次密码不一致";
        }

        adminDO.setPwd(dto.getNewPassword());
        baseMapper.updateById(adminDO);
        return null;
    }

    public void changeInfo(AdminChangeInfoDTO dto, String id){
        AdminDO adminDO = baseMapper.selectById(id);

        adminDO.setTel(dto.getTel());
        adminDO.setMail(dto.getMail());

        baseMapper.updateById(adminDO);
    }
}