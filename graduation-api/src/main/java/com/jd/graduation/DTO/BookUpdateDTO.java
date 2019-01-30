package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BookUpdateDTO {
    @NotNull
    private Integer id;
    @NotNull
    private double price;
    @NotNull
    private String description;
}