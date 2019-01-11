package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class AdminCreateDTO {
    @NotNull
    private String id;
    @NotNull
    private String pwd;
    @NotNull
    private String name;
    @NotNull
    private Integer role;
}