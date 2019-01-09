package com.jd.graduation.serviceimpl;

import com.jd.graduation.service.SysConfigService;
import org.springframework.stereotype.Service;

@Service("SysConfigServiceImpl")
public class SysConfigServiceImpl extends SysConfigService {
//    public boolean changePwd(ChangePasswordModel model) {
//        QueryWrapper<SystemConfigDetail> wrapper = new QueryWrapper<>();
//        wrapper.eq("name", AdminConfigVO.ADMIN_PASSWORD);
//        SystemConfigDetail systemConfigDetail = baseMapper.selectOne(wrapper);
//
//        if (systemConfigDetail.getValue().equals(model.getPrePassword())
//                && model.getNewPassword().equals(model.getNewPasswordAgain())) {
//
//            systemConfigDetail.setValue(model.getNewPassword());
//            int result = baseMapper.update(systemConfigDetail, wrapper);
//
//            return true;
//        }
//
//        return false;
//    }
}