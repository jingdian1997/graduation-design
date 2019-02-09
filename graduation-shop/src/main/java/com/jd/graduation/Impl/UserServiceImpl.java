package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.UserDO;
import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DTO.UserChangeInfoDTO;
import com.jd.graduation.DTO.UserCreateDTO;
import com.jd.graduation.VO.UserVO;
import com.jd.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserServiceImpl")
public class UserServiceImpl extends UserService {
    @Autowired
    private UserLoginServiceImpl userLoginService;

    @Transactional(rollbackFor = Exception.class)
    public void create(UserCreateDTO dto) throws Exception {
        if (!dto.getPwd().equals(dto.getPwdAgain())) {
            throw new Exception("两次密码不一致");
        }
        if (!validNickname(dto.getNickname())) {
            throw new Exception("昵称占用");
        }
        if (!userLoginService.validTel(dto.getTel())) {
            throw new Exception("手机号占用");
        }
        if (!userLoginService.validMail(dto.getEmail())) {
            throw new Exception("邮箱占用");
        }

        UserDO userDO = new UserDO();
        userDO.setNickname(dto.getNickname());
        baseMapper.insert(userDO);

        UserLoginDO userLoginDO = new UserLoginDO();
        userLoginDO.setId(userDO.getId());
        userLoginDO.setTel(dto.getTel());
        userLoginDO.setMail(dto.getEmail());
        userLoginDO.setPwd(dto.getPwd());
        userLoginService.insert(userLoginDO);
    }

    public void changeInfo(UserChangeInfoDTO dto, Integer id) {
        UserDO userDO = baseMapper.selectById(id);

        userDO.setName(dto.getName());
        userDO.setSex(dto.getSex());
        userDO.setBirthday(dto.getBirthday());
        userDO.setIdCard(dto.getIdCard());

        baseMapper.updateById(userDO);
    }

    public UserVO get(Integer id) {
        return baseMapper.getUser(id);
    }

    public boolean validNickname(String nickname) {
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        wrapper.eq("nickname", nickname);

        return baseMapper.selectCount(wrapper) == 0;
    }
}