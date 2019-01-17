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
@TableName(value = "order_refund")
public class OrderRefundDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "oid")
    private Integer oid;
    @TableField(value = "cid")
    private Integer bid;
    @TableField(value = "aid")
    private Integer uid;

    @TableField(value = "amount")
    private Integer amount;
    @TableField(value = "price")
    private Double price;

    @TableField(value = "reason")
    private String reason;
    @TableField(value = "refuse_reason")
    private String refuseReason;

    @TableField(value = "pay")
    private Double pay;

    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "reply_time")
    private Date replyTime;
    @TableField(value = "complete_time")
    private Date completeTime;
}