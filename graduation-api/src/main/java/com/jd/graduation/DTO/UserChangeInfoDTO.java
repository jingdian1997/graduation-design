package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UserChangeInfoDTO {
    @NotNull
    private String nickname;
    @NotNull
    private String name;
    @NotNull
    private String sex;
    @NotNull
    private String idCard;
}