package com.jd.graduation.VO;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StaticAmountVO {
    @TableField(value = "value")
    private Integer value;

    @TableField(value = "name")
    private String name;
}