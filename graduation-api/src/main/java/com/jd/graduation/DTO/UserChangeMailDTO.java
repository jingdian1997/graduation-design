package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;

@Getter
@Setter
@ToString
public class UserChangeMailDTO {
    @Email
    private String mail;
}