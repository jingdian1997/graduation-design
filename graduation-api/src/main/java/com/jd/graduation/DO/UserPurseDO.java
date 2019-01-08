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
@TableName(value = "user_purse")
public class UserPurseDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "balance")
    private Double balance;
    @TableField(value = "pay_pwd")
    private String payPwd;

    @TableField(value = "time")
    private Date time;
}