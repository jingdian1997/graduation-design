package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.BookDO;
import com.jd.graduation.VO.BookVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper extends BaseMapper<BookDO> {
    @Select("SELECT * FROM book as b LEFT JOIN stock as s ON b.id = s.bid " +
            "where b.name like CONCAT('%',#{query},'%')")
    List<BookVO> getBooksByQuery(Page<BookVO> page, @Param("query") String query);

    @Select("SELECT * FROM book as b LEFT JOIN stock as s ON b.id = s.bid ")
    List<BookVO> getBooks(Page<BookVO> page);

    @Select("SELECT * FROM book as b LEFT JOIN stock as s ON b.id = s.bid " +
            "where b.name like CONCAT('%',#{query},'%') and FIND_IN_SET(b.cid, #{ids})")
    List<BookVO> getBooksByCategoryAndQuery(Page<BookVO> page, @Param("query") String query,
                                            @Param("ids") String ids);
    @Select("SELECT * FROM book as b LEFT JOIN stock as s ON b.id = s.bid " +
            "where FIND_IN_SET(b.cid, #{ids})")
    List<BookVO> getBooksByCategory(Page<BookVO> page, @Param("ids") String ids);

    @Select("SELECT * FROM book as b LEFT JOIN stock as s ON b.id = s.bid where b.id=#{id}")
    BookVO getOneBook(@Param("id") Integer id);

    //用户不能看到下架的商品
    @Select("SELECT * FROM book as b LEFT JOIN stock as s ON b.id = s.bid " +
            "where b.name like CONCAT('%',#{query},'%') and del = 0")
    List<BookVO> getBooksByQueryNotDel(Page<BookVO> page, @Param("query") String query);

    @Select("SELECT * FROM book as b LEFT JOIN stock as s ON b.id = s.bid where del = 0")
    List<BookVO> getBooksNotDel(Page<BookVO> page);

    @Select("SELECT * FROM book as b LEFT JOIN stock as s ON b.id = s.bid " +
            "where b.name like CONCAT('%',#{query},'%') and FIND_IN_SET(b.cid, #{ids}) and del = 0")
    List<BookVO> getBooksByQueryAndCategoryNotDel(Page<BookVO> page, @Param("query") String query,
                                                  @Param("ids") String ids);

    @Select("SELECT * FROM book as b LEFT JOIN stock as s ON b.id = s.bid " +
            "where FIND_IN_SET(b.cid, #{ids}) and del = 0")
    List<BookVO> getBooksByCategoryNotDel(Page<BookVO> page, @Param("ids") String ids);

    @Select("SELECT * FROM book as b LEFT JOIN stock as s ON b.id = s.bid where b.id=#{id} and del = 0")
    BookVO getOneBookNotDel(@Param("id") Integer id);
}