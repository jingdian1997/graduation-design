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
@TableName(value = "user")
public class UserDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "nickname")
    private String nickname;
    @TableField(value = "name")
    private String name;

    @TableField(value = "id_card")
    private String idCard;
    @TableField(value = "sex")
    private String sex;

    @TableField(value = "birthday")
    private Date birthday;

    @TableField(value = "time")
    private Date time;
}