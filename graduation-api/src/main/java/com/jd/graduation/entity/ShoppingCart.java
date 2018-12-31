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
@TableName(value = "shopping_cart")
public class ShoppingCart {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "amount")
    private int amount;
    @TableField(value = "sale_price")
    private double salePrice;
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "user_id")
    private int userId;
    @TableField(value = "book_id")
    private int bookId;
}