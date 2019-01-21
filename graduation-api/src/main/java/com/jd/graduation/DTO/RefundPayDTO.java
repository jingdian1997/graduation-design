package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class RefundPayDTO {
    @NotNull
    private Integer id;
    @NotNull
    private double pay;
}