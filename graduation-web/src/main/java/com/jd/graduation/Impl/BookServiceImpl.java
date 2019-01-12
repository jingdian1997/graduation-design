package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.model.response.BookCategoryVO;
import com.jd.graduation.service.BookService;
import org.springframework.stereotype.Service;

@Service("BookServiceImpl")
public class BookServiceImpl extends BookService {
    public Page<BookCategoryVO> selectList(Page<BookCategoryVO> page, Integer categoryId, String query) {
        if (query == null) {
            query = "";
        }

        if (categoryId == null){
            return page.setRecords(baseMapper.getBooksNotDel(page, query));
        }
        return page.setRecords(baseMapper.getBooksByCategoryNotDel(page, query, categoryId));
    }

    public BookCategoryVO selectOne(Integer bookId) {
        return baseMapper.getOneBookNotDel(bookId);
    }
}