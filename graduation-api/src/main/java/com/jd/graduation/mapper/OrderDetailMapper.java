package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jd.graduation.DO.OrderDetailDO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.VO.StaticAmountVO;
import com.jd.graduation.VO.StaticPriceVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrderDetailMapper extends BaseMapper<OrderDetailDO> {
    @Update("update order_detail set flag = #{flag} where oid = #{oid}")
    void updateFlagByOid(@Param("oid") Integer oid, @Param("flag") Integer flag);

    @Select("SELECT b.*, bp.picture FROM `order_detail` as od LEFT JOIN book as b ON od.bid=b.id " +
            "LEFT JOIN book_picture as bp ON bp.id=od.bid WHERE b.del=0 " +
            "GROUP BY od.bid ORDER BY SUM(od.amount) DESC LIMIT #{size}")
    List<BookVO> getMostSold(@Param("size") int size);

    @Select("SELECT b.name as name, SUM(od.amount) as `value` FROM `order_detail` as od " +
            "LEFT JOIN book as b ON od.bid=b.id " +
            "WHERE od.flag>0 GROUP BY od.bid ORDER BY SUM(od.amount) DESC LIMIT 5")
    List<StaticAmountVO> getAmountStatics();

    @Select("SELECT b.name as name, SUM(od.total) as `value` FROM `order_detail` as od " +
            "LEFT JOIN book as b ON od.bid=b.id " +
            "WHERE od.flag>0 GROUP BY od.bid ORDER BY SUM(od.total) DESC LIMIT 5")
    List<StaticPriceVO> getPriceStatics();
}