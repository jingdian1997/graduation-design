package com.jd.graduation.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@TableName(value = "system_config_detail")
public class SystemConfigDetail {
    @TableField(value = "id")
    private int id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "value")
    private String value;
}