package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.UserAddressDO;
import com.jd.graduation.DTO.UserAddressCreateDTO;
import com.jd.graduation.DTO.UserAddressUpdateDTO;
import com.jd.graduation.service.UserAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UserAddressServiceImpl")
public class UserAddressServiceImpl extends UserAddressService {
    public List<UserAddressDO> all(Integer uid) {
        QueryWrapper<UserAddressDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("del", false);
        return baseMapper.selectList(wrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(Integer uid, UserAddressCreateDTO dto) throws Exception{
        UserAddressDO userAddressDO = new UserAddressDO();

        userAddressDO.setUid(uid);
        userAddressDO.setAddress(dto.getAddress());
        userAddressDO.setRecipient(dto.getRecipient());
        userAddressDO.setTel(dto.getTel());
        userAddressDO.setDel(false);

        baseMapper.insert(userAddressDO);

        if (dto.isDefaulting()) {
            setDefault(userAddressDO.getId(), uid);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void setDefault(Integer id, Integer uid) throws Exception {
        UserAddressDO userAddressDO = baseMapper.selectById(id);
        if (userAddressDO == null || userAddressDO.isDel() || !userAddressDO.getUid().equals(uid)){
            throw new Exception("不是你的地址");
        }

        baseMapper.setNotDefault(uid);
        userAddressDO.setDefaulting(true);
        baseMapper.updateById(userAddressDO);
    }

    public void delete(Integer id, Integer uid) throws Exception {
        UserAddressDO userAddressDO = baseMapper.selectById(id);
        if (userAddressDO == null || userAddressDO.isDel() || !userAddressDO.getUid().equals(uid)){
            throw new Exception("不是你的地址");
        }

        if (userAddressDO.isDefaulting()){
            throw new Exception("默认地址不可删除");
        }

        userAddressDO.setDel(true);
        baseMapper.updateById(userAddressDO);
    }

    public void update(UserAddressUpdateDTO dto, Integer uid) throws Exception {
        UserAddressDO userAddressDO = baseMapper.selectById(dto.getId());
        if (userAddressDO == null || userAddressDO.isDel() || !userAddressDO.getUid().equals(uid)){
            throw new Exception("不是你的地址");
        }

        userAddressDO.setTel(dto.getTel());
        userAddressDO.setRecipient(dto.getRecipient());
        userAddressDO.setAddress(dto.getAddress());
        baseMapper.updateById(userAddressDO);
    }

    public UserAddressDO one(Integer id) {
        return baseMapper.selectById(id);
    }
}