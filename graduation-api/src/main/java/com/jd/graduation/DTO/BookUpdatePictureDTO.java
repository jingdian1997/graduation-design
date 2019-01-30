package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class BookUpdatePictureDTO {
    @NotNull
    private Integer id;
    @NotNull
    private String picture;
}
