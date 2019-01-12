package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class Category2CreateDTO {
    @NotNull
    private String name;
    @NotNull
    private Integer cid;
}
