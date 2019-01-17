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
@TableName(value = "user_address")
public class UserAddressDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "uid")
    private Integer uid;

    @TableField(value = "address")
    private String address;
    @TableField(value = "recipient")
    private String recipient;
    @TableField(value = "tel")
    private String tel;

    @TableField(value = "defaulting")
    private boolean defaulting;
    @TableField(value = "del")
    private boolean del;
}