package com.jd.graduation.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@TableName("order_exchange")
public class OrderExchangeDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "oid")
    private Integer oid;
    @TableField(value = "bid")
    private Integer bid;
    @TableField(value = "uid")
    private Integer uid;

    @TableField(value = "amount")
    private Integer amount;

    @TableField(value = "reason")
    private String reason;
    @TableField(value = "refuse_reason")
    private String refuseReason;

    @TableField(value = "pay")
    private Double pay;
    @TableField(value = "flag")
    private Integer flag;

    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "reply_time")
    private Date replyTime;
    @TableField(value = "resend_time")
    private Date resendTime;
    @TableField(value = "pay_time")
    private Date payTime;
    @TableField(value = "complete_time")
    private Date completeTime;

}