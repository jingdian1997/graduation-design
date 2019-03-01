package com.jd.graduation.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jd.graduation.DO.OrderDetailDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderVO {
    private Integer id;

    private String nickname;

    private Double price;
    private Double freight;
    private Double pay;

    private String deliverNo;
    private Integer status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date payTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date confirmTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date deliverTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date completeTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cancelTime;

    private List<OrderDetailDO> details;
}