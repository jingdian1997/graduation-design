package com.jd.graduation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class LoginModel {
    @NotNull
    private String accountName;
    @NotNull
    private String accountPassword;
}