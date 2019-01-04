package com.jd.graduation.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class BookCreateModel {
    @NotNull
    private String name;
    @NotNull
    private String author;
    @NotNull
    private String publisher;
    @NotNull
    private Date publishDate;
    @NotNull
    private double sale;
    @NotNull
    private int categoryId;
    @NotNull
    private String picture;

    private String description;
}