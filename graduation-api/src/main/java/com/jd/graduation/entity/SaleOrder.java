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
@TableName(value = "sale_order")
public class SaleOrder {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "address")
    private String address;
    @TableField(value = "contact")
    private String contact;
    @TableField(value = "telephone")
    private String telephone;

    @TableField(value = "pay")
    private double pay;
    @TableField(value = "state")
    private int state;

    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "pay_time")
    private Date payTime;
    @TableField(value = "deliver_time")
    private Date deliverTime;
    @TableField(value = "complete_time")
    private Date completeTime;
    @TableField(value = "cancel_time")
    private Date cancelTime;

    @TableField(value = "user_id")
    private int userId;
}