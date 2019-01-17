package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UserAddressCreateDTO {
    @NotNull
    private String address;
    @NotNull
    private String recipient;
    @NotNull
    private String tel;

    private boolean defaulting = false;
}