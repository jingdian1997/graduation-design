package com.jd.graduation.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserVO {
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
    @TableField(value = "introduce")
    private String introduce;

    @TableField(value = "time")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date time;

    @TableField(value = "tel")
    private String tel;
    @TableField(value = "mail")
    private String mail;
}