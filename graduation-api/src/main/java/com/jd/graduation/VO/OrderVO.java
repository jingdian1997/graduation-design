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
public class OrderVO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "aid")
    private Integer aid;
    @TableField(value = "address")
    private String address;
    @TableField(value = "recipient")
    private String recipient;
    @TableField(value = "tel")
    private String tel;

    @TableField(value = "price")
    private Double price;
    @TableField(value = "freight")
    private Double freight;
    @TableField(value = "pay")
    private Double pay;

    @TableField(value = "deliver_no")
    private Double deliverNo;
    @TableField(value = "`status`")
    private Integer status;

    @TableField(value = "create_time")
    private Date createTime;
    @TableField(value = "pay_time")
    private Date payTime;
    @TableField(value = "confirm_time")
    private Date confirmTime;
    @TableField(value = "deliver_time")
    private Date deliverTime;
    @TableField(value = "complete_time")
    private Date completeTime;
    @TableField(value = "cancel_time")
    private Date cancelTime;
}