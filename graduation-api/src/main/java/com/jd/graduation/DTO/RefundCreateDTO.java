package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class RefundCreateDTO {
    @NotNull
    private Integer odid;
    @NotNull
    private Integer amount;
    @NotNull
    private String reason;
    @NotNull
    private Integer type;
}