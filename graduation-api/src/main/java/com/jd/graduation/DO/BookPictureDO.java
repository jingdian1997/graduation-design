package com.jd.graduation.DO;

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
@TableName(value = "book_picture")
public class BookPictureDO {
    @TableId(value = "id")
    private Integer id;

    @TableField(value = "picture")
    private String picture;
}