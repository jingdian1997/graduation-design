package com.jd.graduation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UserCreateModel {
    @NotNull
    private String accountName;
    @NotNull
    private String accountPassword;
    @NotNull
    private String accountPasswordAgain;
    @NotNull
    private String name;
    @NotNull
    private String sex;
    @NotNull
    private String idCard;
    @NotNull
    private String telephone;
    @Email
    private String email;
}