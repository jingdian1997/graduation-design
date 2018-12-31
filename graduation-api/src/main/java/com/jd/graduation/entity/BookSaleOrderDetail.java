package com.jd.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Getter
@Setter
@ToString
@TableName(value = "book_sale_order_detail")
public class BookSaleOrderDetail {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "amount")
    private int amount;
    @TableField(value = "order_state")
    private int orderState;
    @TableField(value = "sale_price")
    private double salePrice;

    @TableField(value = "user_id")
    private int userId;
    @TableField(value = "book_id")
    private int bookId;
    @TableField(value = "sale_id")
    private int saleId;
}