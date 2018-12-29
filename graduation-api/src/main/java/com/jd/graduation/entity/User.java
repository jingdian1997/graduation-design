package com.jd.graduation.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class User {
    private int id;
    private String accountName;
    private String accountPassword;
    private String name;
    private String sex;
    private String IDCard;
    private String telephone;
    private String mail;
    private Role role;

    public User(int id, String accountName, String accountPassword, String name, String sex,
                String IDCard, String telephone, String mail, String role) {
        this.id = id;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.name = name;
        this.sex = sex;
        this.IDCard = IDCard;
        this.telephone = telephone;
        this.mail = mail;
        this.role = Role.valueOf(role);
    }

    public void setRole(String role){
        this.role = Role.valueOf(role);
    }
}