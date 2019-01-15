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
@TableName(value = "stock")
public class StockDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "bid")
    private Integer bid;

    @TableField(value = "stock")
    private Integer stock;
}