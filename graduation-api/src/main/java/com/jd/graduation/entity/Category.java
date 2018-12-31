package com.jd.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Getter
@Setter
@ToString
@TableName(value = "category")
public class Category {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "name")
    private String name;
}