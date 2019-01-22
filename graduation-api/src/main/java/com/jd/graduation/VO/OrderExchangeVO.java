package com.jd.graduation.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OrderExchangeVO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "odid")
    private Integer odid;
    @TableField(value = "bid")
    private Integer bid;
    @TableField(value = "bname")
    private String bname;

    @TableField(value = "uid")
    private Integer uid;
    @TableField(value = "nickname")
    private Integer nickname;

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
    @TableField(value = "flag")
    private Integer flag;

    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "reply_time")
    private Date replyTime;
    @TableField(value = "pay_time")
    private Date payTime;
    @TableField(value = "complete_time")
    private Date completeTime;
}