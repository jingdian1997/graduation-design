package com.jd.graduation.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@TableName(value = "order_detail")
public class OrderDetailDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "oid")
    private Integer oid;
    @TableField(value = "bid")
    private Integer bid;
    @TableField(value = "bname")
    private String bname;
    @TableField(value = "uid")
    private Integer uid;

    @TableField(value = "amount")
    private Integer amount;
    @TableField(value = "price")
    private Double price;
    @TableField(value = "total")
    private Double total;

    //冗余order status字段
    @TableField(value = "flag")
    private Integer flag;
    @TableField(value = "if_comment")
    private boolean ifComment;
}