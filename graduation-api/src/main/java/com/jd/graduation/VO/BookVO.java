package com.jd.graduation.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "cid")
    private Integer cid;

    @TableField(value = "name")
    private String name;
    @TableField(value = "author")
    private String author;
    @TableField(value = "ISBN")
    private String ISBN;

    @TableField(value = "publisher")
    private String publisher;
    @TableField(value = "publish_date")
    private Date publishDate;
    @TableField(value = "description")
    private String description;

    @TableField(value = "price")
    private Double price;
    @TableField(value = "discount")
    private Double discount;
    @TableField(value = "picture")
    private String picture;

    @TableField(value = "del")
    private boolean del;
    @TableField(value = "stock")
    private Integer stock;
    @TableField(value = "free_stock")
    private Integer freeStock;

    private List<CategoryDO> categories;
}