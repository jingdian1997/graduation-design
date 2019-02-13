package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CommentCreateDTO {
    @NotNull
    private Integer bid;
    @NotNull
    private Integer score;
    @NotNull
    private Integer odid;

    private String content;
}