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
@TableName(value = "purchase_refund")
public class PurchaseRefund {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "amount")
    private int amount;
    @TableField(value = "price")
    private double price;

    @TableField(value = "pay")
    private double pay;
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "purchase_id")
    private int purchase_id;
    @TableField(value = "book_id")
    private int bookId;
    @TableField(value = "supplier_id")
    private int supplierId;
}