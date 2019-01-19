package com.jd.graduation.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailVO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "oid")
    private Integer oid;
    @TableField(value = "bid")
    private Integer bid;
    @TableField(value = "bname")
    private String bname;

    @TableField(value = "amount")
    private Integer amount;
    @TableField(value = "price")
    private Double price;
    @TableField(value = "total")
    private Double total;

    @TableField(value = "flag")
    private Integer flag;
}