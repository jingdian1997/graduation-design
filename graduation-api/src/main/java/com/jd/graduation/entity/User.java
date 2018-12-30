package com.jd.graduation.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class User implements Serializable {
    @TableField(value = "id")
    private int id;
    @TableField(value = "account_name")
    private String accountName;
    @TableField(value = "account_password")
    private String accountPassword;
    @TableField(value = "name")
    private String name;
    @TableField(value = "sex")
    private String sex;
    @TableField(value = "ID_card")
    private String idCard;
    @TableField(value = "telephone")
    private String telephone;
    @TableField(value = "mail")
    private String mail;
    @TableField(value = "role")
    private Role role;

    public User(int id, String accountName, String accountPassword, String name, String sex,
                String idCard, String telephone, String mail, String role) {
        this.id = id;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.name = name;
        this.sex = sex;
        this.idCard = idCard;
        this.telephone = telephone;
        this.mail = mail;
        this.role = Role.valueOf(role);
    }

    public void setRole(String role){
        this.role = Role.valueOf(role);
    }
}