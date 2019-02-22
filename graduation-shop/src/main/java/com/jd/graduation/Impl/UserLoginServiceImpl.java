package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.ChangePasswordDTO;
import com.jd.graduation.DTO.UserChangeMailDTO;
import com.jd.graduation.DTO.UserChangeTelDTO;
import com.jd.graduation.VO.UserVO;
import com.jd.graduation.service.AuthenticationService;
import com.jd.graduation.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("UserLoginServiceImpl")
public class UserLoginServiceImpl extends UserLoginService {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private AuthenticationService authenticationService;

    public int insert(UserLoginDO userLoginDO) {
        return baseMapper.insert(userLoginDO);
    }

    public Map<String, Object> login(String account, String pwd) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<UserLoginDO> wrapper = new QueryWrapper<>();

        //构造相等条件，注意这里的字段为数据表中的字段，而不是Java实体类
        if (account.contains("@")) {
            //是邮箱登录
            wrapper.eq("mail", account);
        }else {
            wrapper.eq("tel", account);
        }

        UserLoginDO userLoginDO = baseMapper.selectOne(wrapper);
        if (userLoginDO != null && pwd.equals(userLoginDO.getPwd())){
            //不存密码
            userLoginDO.setPwd(null);

            String key = authenticationService.makeToken(userLoginDO.getTel());
            authenticationService.set(key, userLoginDO);

            UserVO userVO = userService.get(userLoginDO.getId());

            map.put("key", key);
            map.put("user", userVO);
            return map;
        }

        map.put("key", null);
        return map;
    }

    public void logout(String key) {
        authenticationService.delete(key);
    }

    public void changePwd(Integer id, ChangePasswordDTO dto) throws Exception {
        UserLoginDO userLoginDO = baseMapper.selectById(id);
        String prePwd = userLoginDO.getPwd();

        if (!dto.getPrePassword().equals(prePwd)){
            throw new Exception("原密码错误");
        }
        if (prePwd.equals(dto.getNewPassword())){
            throw new Exception("新密码不能与原密码相同");
        }
        if (!dto.getNewPassword().equals(dto.getNewPasswordAgain())) {
            throw new Exception("两次密码不一致");
        }

        userLoginDO.setPwd(dto.getNewPassword());
        baseMapper.updateById(userLoginDO);
    }

    public void changeMail(UserChangeMailDTO dto, Integer id, String key) throws Exception {
        UserLoginDO userLoginDO = baseMapper.selectById(id);

        if (dto.getMail().equals(userLoginDO.getMail())) {
            throw new Exception("相同的邮箱");
        }

        userLoginDO.setMail(dto.getMail());
        baseMapper.updateById(userLoginDO);
        //重置缓存
        userLoginDO.setPwd(null);
        authenticationService.set(key, userLoginDO);
    }

    public void changeTel(UserChangeTelDTO dto, Integer id, String key) throws Exception {
        UserLoginDO userLoginDO = baseMapper.selectById(id);

        if (dto.getTel().equals(userLoginDO.getTel())) {
            throw new Exception("相同的电话号码");
        }

        userLoginDO.setTel(dto.getTel());
        baseMapper.updateById(userLoginDO);
        //重置缓存
        userLoginDO.setPwd(null);
        authenticationService.set(key, userLoginDO);
    }

    public boolean validTel(String tel) {
        QueryWrapper<UserLoginDO> wrapper = new QueryWrapper<>();
        wrapper.eq("tel", tel);

        return baseMapper.selectCount(wrapper) == 0;
    }

    public boolean validMail(String mail) {
        QueryWrapper<UserLoginDO> wrapper = new QueryWrapper<>();
        wrapper.eq("mail", mail);

        return baseMapper.selectCount(wrapper) == 0;
    }
}