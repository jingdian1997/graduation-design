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

    @TableField(value = "odid")
    private Integer odid;
    @TableField(value = "bid")
    private Integer bid;
    @TableField(value = "uid")
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

    @TableField(value = "type")
    private Integer type;
    @TableField(value = "flag")
    private Integer flag;

    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "reply_time")
    private Date replyTime;
    @TableField(value = "deal_time")
    private Date dealTime;
    @TableField(value = "complete_time")
    private Date completeTime;
}