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
@TableName(value = "sale_refund")
public class SaleRefund {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "amount")
    private int amount;
    @TableField(value = "price")
    private double price;
    @TableField(value = "return_reason")
    private String returnReason;

    @TableField(value = "refuse_reason")
    private String refuseReason;
    @TableField(value = "pay")
    private double pay;
    @TableField(value = "state")
    private int state;

    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "operate_time")
    private Date operateTime;
    @TableField(value = "complete_time")
    private Date completeTime;

    @TableField(value = "sale_id")
    private int sale_id;
    @TableField(value = "book_id")
    private int bookId;
    @TableField(value = "user_id")
    private int userId;
}