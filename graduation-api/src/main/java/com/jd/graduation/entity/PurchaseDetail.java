package com.jd.graduation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName(value = "purchase_detail")
public class PurchaseDetail {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "amount")
    private int amount;
    @TableField(value = "price")
    private double price;
    @TableField(value = "pay")
    private double pay;

    @TableField(value = "purchase_id")
    private int purchaseId;
    @TableField(value = "book_id")
    private int bookId;
}