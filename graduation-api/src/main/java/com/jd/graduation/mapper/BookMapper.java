package com.jd.graduation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.entity.Book;
import com.jd.graduation.model.response.BookCategoryVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BookMapper extends BaseMapper<Book> {
    @Select("select a.*, c.`name` as category_name from book as a " +
            "left join category as c on (c.id = a.category_id)")
    List<BookCategoryVO> getBooks(Page<BookCategoryVO> page);
}