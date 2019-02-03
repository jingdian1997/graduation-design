package com.jd.graduation.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CategoryVO implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "name")
    private String name;
    @TableField(value = "parent_id")
    private Integer parentId;

    @TableField(value = "del")
    private boolean del;

    @TableField(exist = false)
    private List<CategoryVO> children;
}