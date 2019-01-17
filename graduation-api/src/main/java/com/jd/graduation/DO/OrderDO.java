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
@TableName(value = "`order`")
public class OrderDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "uid")
    private Integer uid;
    @TableField(value = "aid")
    private Integer aid;

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