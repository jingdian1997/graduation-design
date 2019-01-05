package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.entity.Book;
import com.jd.graduation.model.response.BookCategoryVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper extends BaseMapper<Book> {
    @Select("select a.*, c.`name` as category_name from book as a " +
            "left join category as c on (c.id = a.category_id) where a.name like CONCAT('%',#{query},'%')")
    List<BookCategoryVO> getBooks(Page<BookCategoryVO> page, @Param("query") String query);

    @Select("select a.*, c.`name` as category_name from book as a " +
            "left join category as c on (c.id = a.category_id) where c.id = #{cid} and " +
            "a.name like CONCAT('%',#{query},'%')")
    List<BookCategoryVO> getBooksByCategory(Page<BookCategoryVO> page, @Param("query") String query,
                                            @Param("cid") Integer cid);

    @Select("select a.*, c.`name` as category_name from book as a " +
            "left join category as c on (c.id = a.category_id) where a.id=#{id}")
    BookCategoryVO getOneBook(@Param("id") Integer id);

    //用户是否不能看到下架的商品?
    @Select("select a.*, c.`name` as category_name from book as a " +
            "left join category as c on (c.id = a.category_id) where " +
            "a.name like CONCAT('%',#{query},'%') and a.del = 0")
    List<BookCategoryVO> getBooksNotDel(Page<BookCategoryVO> page, @Param("query") String query);

    @Select("select a.*, c.`name` as category_name from book as a " +
            "left join category as c on (c.id = a.category_id) where c.id = #{cid} and " +
            "a.name like CONCAT('%',#{query},'%') and a.del = 0")
    List<BookCategoryVO> getBooksByCategoryNotDel(Page<BookCategoryVO> page, @Param("query") String query,
                                            @Param("cid") Integer cid);

    @Select("select a.*, c.`name` as category_name from book as a " +
            "left join category as c on (c.id = a.category_id) where a.id=#{id} and a.del = 0")
    BookCategoryVO getOneBookNotDel(@Param("id") Integer id);
}