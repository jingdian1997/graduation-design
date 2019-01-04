package com.jd.graduation.model.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class BookCategoryVO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "author")
    private String author;
    @TableField(value = "publisher")
    private String publisher;
    @TableField(value = "publish_date")
    private Date publishDate;
    @TableField(value = "description")
    private String description;
    @TableField(value = "sale")
    private double sale;
    @TableField(value = "stock")
    private int stock;
    @TableField(value = "picture")
    private String picture;
    @TableField(value = "del")
    private boolean del;

    @TableField(value = "category_id")
    private int categoryId;
    @TableField(value = "category_name")
    private String categoryName;
}