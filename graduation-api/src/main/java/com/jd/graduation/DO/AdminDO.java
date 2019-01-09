package com.jd.graduation.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName(value = "admin")
public class AdminDO implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @TableField(value = "pwd")
    private String pwd;
    @TableField(value = "name")
    private String name;
    @TableField(value = "tel")
    private String tel;

    @TableField(value = "role")
    private Integer role;
}