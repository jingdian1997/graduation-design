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
@TableName(value = "cart")
public class CartDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "uid")
    private Integer uid;
    @TableField(value = "bid")
    private Integer bid;

    @TableField(value = "price")
    private Double price;
    @TableField(value = "amount")
    private Integer amount;
    @TableField(value = "create_time")
    private Date createTime;
}