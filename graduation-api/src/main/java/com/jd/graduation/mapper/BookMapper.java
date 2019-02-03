package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.BookDO;
import com.jd.graduation.VO.BookVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper extends BaseMapper<BookDO> {
    @Select("SELECT * FROM `book` as b " +
            "LEFT JOIN `book_picture` as bp ON b.id=bp.id " +
            "LEFT JOIN `stock` as s ON b.id=s.bid " +
            "where b.name like CONCAT('%',#{query},'%') ORDER BY b.del")
    List<BookVO> getBooksByQuery(Page<BookVO> page, @Param("query") String query);

    @Select("SELECT * FROM `book` as b " +
            "LEFT JOIN `book_picture` as bp ON b.id=bp.id " +
            "LEFT JOIN `stock` as s ON b.id=s.bid ORDER BY b.del")
    List<BookVO> getBooks(Page<BookVO> page);

    @Select("SELECT * FROM `book` as b " +
            "LEFT JOIN `book_picture` as bp ON b.id=bp.id " +
            "LEFT JOIN `stock` as s ON b.id=s.bid " +
            "where b.name like CONCAT('%',#{query},'%') " +
            "and FIND_IN_SET(b.cid, #{ids}) ORDER BY b.del")
    List<BookVO> getBooksByCategoryAndQuery(Page<BookVO> page, @Param("query") String query,
                                            @Param("ids") String ids);
    @Select("SELECT * FROM `book` as b " +
            "LEFT JOIN `book_picture` as bp ON b.id=bp.id " +
            "LEFT JOIN `stock` as s ON b.id=s.bid " +
            "where FIND_IN_SET(b.cid, #{ids}) ORDER BY b.del")
    List<BookVO> getBooksByCategory(Page<BookVO> page, @Param("ids") String ids);

    @Select("SELECT * FROM `book` as b " +
            "LEFT JOIN `book_picture` as bp ON b.id=bp.id " +
            "LEFT JOIN `stock` as s ON b.id=s.bid where b.id=#{id}")
    BookVO getOneBook(@Param("id") Integer id);

    @Select("SELECT * FROM `book` as b " +
            "LEFT JOIN `book_picture` as bp ON b.id=bp.id " +
            "LEFT JOIN `stock` as s ON b.id=s.bid " +
            "where FIND_IN_SET(b.cid, #{ids}) and b.del=0 order by b.create_time desc")
    List<BookVO> getBooksByCategoryNotDel(Page<BookVO> page, @Param("ids") String ids);

    @Select("SELECT * FROM `book` as b " +
            "LEFT JOIN `book_picture` as bp ON b.id=bp.id " +
            "LEFT JOIN `stock` as s ON b.id=s.bid order by b.create_time desc")
    List<BookVO> getNewBooks(Page<BookVO> page);
}