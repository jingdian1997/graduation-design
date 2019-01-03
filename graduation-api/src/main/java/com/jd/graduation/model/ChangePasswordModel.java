package com.jd.graduation.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class ChangePasswordModel {
    @NotNull
    private String prePassword;
    @NotNull
    private String newPassword;
    @NotNull
    private String newPasswordAgain;
}