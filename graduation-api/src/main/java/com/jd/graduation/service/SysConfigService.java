package com.jd.graduation.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jd.graduation.DO.SysConfigDO;
import com.jd.graduation.mapper.SysConfigMapper;

public abstract class SysConfigService extends ServiceImpl<SysConfigMapper, SysConfigDO> {
//    public AdminConfigVO getAdminConfig() {
//        QueryWrapper<SystemConfigDetail> wrapper = new QueryWrapper<>();
//        QueryWrapper<SystemConfigDetail> wrapper1 = new QueryWrapper<>();
//
//        wrapper.eq("name", AdminConfigVO.ADMIN_ACCOUNT);
//        wrapper1.eq("name", AdminConfigVO.ADMIN_PASSWORD);
//
//        SystemConfigDetail systemConfigDetail = baseMapper.selectOne(wrapper);
//        SystemConfigDetail systemConfigDetail1 = baseMapper.selectOne(wrapper1);
//
//        AdminConfigVO adminConfigVO = new AdminConfigVO();
//        adminConfigVO.setAccount(systemConfigDetail.getValue());
//        adminConfigVO.setPassword(systemConfigDetail1.getValue());
//
//        return adminConfigVO;
//    }
}