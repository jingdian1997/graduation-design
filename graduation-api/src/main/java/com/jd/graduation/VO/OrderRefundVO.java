package com.jd.graduation.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class OrderRefundVO {
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
    private String nickname;

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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @TableField(value = "reply_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date replyTime;

    @TableField(value = "deal_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dealTime;
}