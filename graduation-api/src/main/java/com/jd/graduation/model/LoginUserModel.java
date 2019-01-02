package com.jd.graduation.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginUserModel {
    @NotNull
    private String accountName;
    @NotNull
    private String accountPassword;
}