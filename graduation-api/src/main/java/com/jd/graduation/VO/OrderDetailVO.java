package com.jd.graduation.VO;

import com.jd.graduation.DO.BookDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDetailVO {
    private Integer id;

    private Integer oid;
    private Integer bid;

    private BookDO bookDO;

    private Integer amount;
    private Double price;
    private Double total;

    private Integer flag;
}