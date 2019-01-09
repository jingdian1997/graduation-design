package com.jd.graduation.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName(value = "book")
public class BookDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "cid")
    private Integer cid;
    @TableField(value = "c2id")
    private Integer c2id;
    @TableField(value = "pid")
    private Integer pid;

    @TableField(value = "name")
    private String name;
    @TableField(value = "author")
    private String author;
    @TableField(value = "ISBN")
    private String ISBN;

    @TableField(value = "publish_date")
    private Date publishDate;
    @TableField(value = "description")
    private String description;
    @TableField(value = "price")
    private Double price;

    @TableField(value = "picture")
    private String picture;

    @TableField(value = "del")
    private boolean del;
}