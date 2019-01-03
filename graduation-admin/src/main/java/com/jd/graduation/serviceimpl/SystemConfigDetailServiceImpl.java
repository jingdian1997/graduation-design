package com.jd.graduation.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.entity.SystemConfigDetail;
import com.jd.graduation.model.AdminConfigVO;
import com.jd.graduation.model.ChangePasswordModel;
import com.jd.graduation.service.SystemConfigDetailService;
import org.springframework.stereotype.Service;

@Service("SystemConfigDetailServiceImpl")
public class SystemConfigDetailServiceImpl extends SystemConfigDetailService {
    public boolean changePwd(ChangePasswordModel model) {
        QueryWrapper<SystemConfigDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("name", AdminConfigVO.ADMIN_PASSWORD);
        SystemConfigDetail systemConfigDetail = baseMapper.selectOne(wrapper);

        if (systemConfigDetail.getValue().equals(model.getPrePassword())
                && model.getNewPassword().equals(model.getNewPasswordAgain())) {

            systemConfigDetail.setValue(model.getNewPassword());
            int result = baseMapper.update(systemConfigDetail, wrapper);

            return true;
        }

        return false;
    }
}