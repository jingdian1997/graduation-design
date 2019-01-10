package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserChangeInfoDTO {
    @NotNull
    private String nickname;

    private String name;
    private String sex;
    private String idCard;
    private Date birthday;
    private String introduce;
}