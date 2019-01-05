package com.jd.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@TableName(value = "cart")
public class Cart {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "price")
    private double price;
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "user_id")
    private int userId;
    @TableField(value = "book_id")
    private int bookId;
}