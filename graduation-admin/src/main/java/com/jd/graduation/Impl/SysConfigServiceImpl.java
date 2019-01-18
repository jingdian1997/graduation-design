package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.SysConfigDO;
import com.jd.graduation.DO.SysConfigKey;
import com.jd.graduation.service.SysConfigService;
import org.springframework.stereotype.Service;

@Service("SysConfigServiceImpl")
public class SysConfigServiceImpl extends SysConfigService {
    public String getValueString(SysConfigKey key){
        return getValue(key);
    }

    public Integer getValueInteger(SysConfigKey key){
        String result = getValue(key);
        return Integer.valueOf(result);
    }

    public Double getValueDouble(SysConfigKey key){
        String result = getValue(key);
        return Double.valueOf(result);
    }

    private String getValue(SysConfigKey key) {
        QueryWrapper<SysConfigDO> wrapper = new QueryWrapper<>();
        wrapper.eq("name", key.getKey());
        return baseMapper.selectOne(wrapper).getValue();
    }
}