package com.jd.graduation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UserChangeInfoModel {
    @NotNull
    private String telephone;
    @Email
    private String mail;
}