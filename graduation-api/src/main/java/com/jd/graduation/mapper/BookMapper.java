package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.BookDO;
import com.jd.graduation.VO.BookVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper extends BaseMapper<BookDO> {
    @Select("SELECT b.*, c.`name` as cname, c2.`name` as c2name FROM " +
            "(`book` as b LEFT JOIN category as c ON b.cid=c.id) " +
            "LEFT JOIN category2 AS c2 ON b.c2id=c2.id " +
            "where b.name like CONCAT('%',#{query},'%')")
    List<BookVO> getBooks(Page<BookVO> page, @Param("query") String query);

    @Select("SELECT b.*, c.`name` as cname, c2.`name` as c2name FROM " +
            "(`book` as b LEFT JOIN category as c ON b.cid=c.id) " +
            "LEFT JOIN category2 AS c2 ON b.c2id=c2.id where c.id = #{cid} and " +
            "b.name like CONCAT('%',#{query},'%')")
    List<BookVO> getBooksByCategory(Page<BookVO> page, @Param("query") String query,
                                            @Param("cid") Integer cid);

    @Select("SELECT b.*, c.`name` as cname, c2.`name` as c2name FROM " +
            "(`book` as b LEFT JOIN category as c ON b.cid=c.id) " +
            "LEFT JOIN category2 AS c2 ON b.c2id=c2.id where c.id = #{cid} and c2.id = #{c2id} and " +
            "b.name like CONCAT('%',#{query},'%')")
    List<BookVO> getBooksByCategory2(Page<BookVO> page, @Param("query") String query,
                                    @Param("cid") Integer cid, @Param("c2id") Integer c2id);

    @Select("SELECT b.*, c.`name` as cname, c2.`name` as c2name FROM " +
            "(`book` as b LEFT JOIN category as c ON b.cid=c.id) " +
            "LEFT JOIN category2 AS c2 ON b.c2id=c2.id where b.id=#{id}")
    BookVO getOneBook(@Param("id") Integer id);

//    //用户是否不能看到下架的商品?
//    @Select("select a.*, c.`name` as category_name from book as a " +
//            "left join category as c on (c.id = a.category_id) where " +
//            "a.name like CONCAT('%',#{query},'%') and a.del = 0")
//    List<BookCategoryVO> getBooksNotDel(Page<BookCategoryVO> page, @Param("query") String query);
//
//    @Select("select a.*, c.`name` as category_name from book as a " +
//            "left join category as c on (c.id = a.category_id) where c.id = #{cid} and " +
//            "a.name like CONCAT('%',#{query},'%') and a.del = 0")
//    List<BookCategoryVO> getBooksByCategoryNotDel(Page<BookCategoryVO> page, @Param("query") String query,
//                                            @Param("cid") Integer cid);
//
//    @Select("select a.*, c.`name` as category_name from book as a " +
//            "left join category as c on (c.id = a.category_id) where a.id=#{id} and a.del = 0")
//    BookCategoryVO getOneBookNotDel(@Param("id") Integer id);
}