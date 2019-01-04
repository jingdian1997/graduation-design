package com.jd.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Getter
@Setter
@ToString
@TableName(value = "sale_order_detail")
public class SaleOrderDetail {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "amount")
    private int amount;
    @TableField(value = "price")
    private double price;
    @TableField(value = "pay")
    private double pay;

    @TableField(value = "sale_id")
    private int saleId;
    @TableField(value = "book_id")
    private int bookId;
}