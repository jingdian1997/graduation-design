package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class BookUpdateDTO {
    @NotNull
    private Integer id;
//    @NotNull
//    private String name;
//    @NotNull
//    private String author;
//    @NotNull
//    private String publisher;
//    @NotNull
//    private Date publishDate;
    @NotNull
    private double price;
    @NotNull
    private String picture;
    @NotNull
    private String description;
}