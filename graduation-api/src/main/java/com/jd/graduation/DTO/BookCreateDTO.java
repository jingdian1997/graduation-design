package com.jd.graduation.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class BookCreateDTO {
    @NotNull
    private String ISBN;
    @NotNull
    private String name;
    @NotNull
    private String author;
    @NotNull
    private String publisher;
    @NotNull
    private Date publishDate;
    @NotNull
    private double price;
    @NotNull
    private double discount;
    @NotNull
    private String picture;
    @NotNull
    private String description;

    @NotNull
    private Integer cid;
}