package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class UserCreateDTO {
    @NotNull
    private String pwd;
    @NotNull
    private String pwdAgain;

    @NotNull
    @Size(min = 6, max = 6)
    private String payPwd;
    @NotNull
    @Size(min = 6, max = 6)
    private String payPwdAgain;

    @NotNull
    private String nickname;
    @NotNull
    private String tel;
    @Email
    private String email;
}