package com.jd.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@TableName(value = "user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "account_name")
    private String accountName;
    @TableField(value = "account_password")
    private String accountPassword;
    @TableField(value = "name")
    private String name;
    @TableField(value = "sex")
    private String sex;
    @TableField(value = "id_card")
    private String idCard;
    @TableField(value = "telephone")
    private String telephone;
    @TableField(value = "mail")
    private String mail;
}