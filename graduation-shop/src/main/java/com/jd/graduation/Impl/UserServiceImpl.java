package com.jd.graduation.Impl;

import com.jd.graduation.DO.UserDO;
import com.jd.graduation.DO.UserLoginDO;
import com.jd.graduation.DO.UserPurseDO;
import com.jd.graduation.DTO.UserChangeInfoDTO;
import com.jd.graduation.DTO.UserChangeMailAndTelDTO;
import com.jd.graduation.DTO.UserCreateDTO;
import com.jd.graduation.VO.UserVO;
import com.jd.graduation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserServiceImpl")
public class UserServiceImpl extends UserService {
    @Autowired
    private UserPurseServiceImpl userPurseService;
    @Autowired
    private UserLoginServiceImpl userLoginService;

    public UserVO getWholeUser(Integer id) {
        return baseMapper.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public String create(UserCreateDTO dto) {
        if (!dto.getPwd().equals(dto.getPwdAgain())) {
            return "两次输入的密码不一致";
        }
        if (!dto.getPayPwd().equals(dto.getPayPwdAgain())) {
            return "两次输入的支付密码不一致";
        }

        UserDO userDO = new UserDO();
        userDO.setNickname(dto.getNickname());
        baseMapper.insert(userDO);

        UserPurseDO userPurseDO = new UserPurseDO();
        userPurseDO.setId(userDO.getId());
        userPurseDO.setPayPwd(dto.getPayPwd());
        userPurseService.insert(userPurseDO);

        UserLoginDO userLoginDO = new UserLoginDO();
        userLoginDO.setId(userDO.getId());
        userLoginDO.setTel(dto.getTel());
        userLoginDO.setMail(dto.getEmail());
        userLoginDO.setPwd(dto.getPwd());
        userLoginService.insert(userLoginDO);

        return null;
    }

    public void changeInfo(UserChangeInfoDTO dto, Integer id) {
        UserDO userDO = baseMapper.selectById(id);

        userDO.setNickname(dto.getNickname());
        userDO.setName(dto.getName());
        userDO.setSex(dto.getSex());
        userDO.setBirthday(dto.getBirthday());
        userDO.setIdCard(dto.getIdCard());
        userDO.setIntroduce(dto.getIntroduce());

        baseMapper.updateById(userDO);
    }

    public void changeMailAndTel(UserChangeMailAndTelDTO dto, Integer id) {

    }
}