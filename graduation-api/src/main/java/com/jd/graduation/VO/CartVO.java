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
public class CartVO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "uid")
    private Integer uid;
    @TableField(value = "bid")
    private Integer bid;

    @TableField(value = "amount")
    private Integer amount;
    @TableField(value = "price")
    private Double price;

    @TableField(value = "create_time")
    private Date createTime;
}