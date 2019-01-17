package com.jd.graduation.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@TableName("comment")
public class CommentDO {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "bid")
    private Integer bid;
    @TableField(value = "uid")
    private Integer uid;

    @TableField(value = "score")
    private Integer score;
    @TableField(value = "content")
    private String content;
    @TableField(value = "reply")
    private String reply;

    @TableField(value = "time")
    private Date time;
    @TableField(value = "reply_time")
    private Date replyTime;
}