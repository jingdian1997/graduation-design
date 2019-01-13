package com.jd.graduation.VO;

import com.jd.graduation.DO.CategoryDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class BookVO {
    private Integer id;

    private String name;
    private String author;
    private String ISBN;

    private String publisher;
    private Date publishDate;
    private String description;

    private Double price;
    private Double discount;
    private String picture;

    private boolean del;

    private List<CategoryDO> categories;
}