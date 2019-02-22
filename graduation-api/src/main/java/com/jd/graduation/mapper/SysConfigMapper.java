package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.SysConfigDO;
import org.apache.ibatis.annotations.Update;

public interface SysConfigMapper extends BaseMapper<SysConfigDO> {
    @Update("update sys_config set value = value+1 where name='CATEGORY_VERSION'")
    void raiseVersion();
}