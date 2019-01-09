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
@TableName(value = "stock_in_log")
public class StockInLogDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "bid")
    private Integer bid;

    @TableField(value = "amount")
    private Integer amount;
    @TableField(value = "status")
    private Integer status;

    @TableField(value = "time")
    private Date time;
}