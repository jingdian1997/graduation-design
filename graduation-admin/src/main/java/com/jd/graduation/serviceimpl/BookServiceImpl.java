package com.jd.graduation.serviceimpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.entity.Book;
import com.jd.graduation.model.request.BookCreateModel;
import com.jd.graduation.model.request.BookUpdateModel;
import com.jd.graduation.model.response.BookCategoryVO;
import com.jd.graduation.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BookServiceImpl")
public class BookServiceImpl extends BookService {
    public int countBookByCategory(int categoryId) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);
        queryWrapper.eq("del", 0);

        return baseMapper.selectCount(queryWrapper);
    }

    public void insert(BookCreateModel model) {
        Book book = new Book();

        book.setName(model.getName());
        book.setAuthor(model.getAuthor());
        book.setPublisher(model.getPublisher());
        book.setPublishDate(model.getPublishDate());
        book.setDescription(model.getDescription());
        book.setSale(model.getSale());
        book.setPicture(model.getPicture());
        book.setCategoryId(model.getCategoryId());
        book.setDel(false);
        book.setStock(0);

        baseMapper.insert(book);
    }

    public void update(BookUpdateModel model) {
        Book book = baseMapper.selectById(model.getId());
        //set fields,exclude id and stock
        if (book != null) {

            book.setDel(false);
            book.setName(model.getName());
            book.setAuthor(model.getAuthor());
            book.setPublisher(model.getPublisher());
            book.setPublishDate(model.getPublishDate());
            book.setPicture(model.getPicture());
            book.setDescription(model.getDescription());
            book.setSale(model.getSale());
            book.setCategoryId(model.getCategoryId());

            baseMapper.updateById(book);
        }
    }

    public void pullOff(int bookId) {
        Book book = baseMapper.selectById(bookId);

        book.setDel(true);
        baseMapper.updateById(book);
    }

    public Page<BookCategoryVO> selectList(Page<BookCategoryVO> page) {
        return page.setRecords(baseMapper.getBooks(page));
    }

    public Book selectOne(int bookId) {
        return null;
    }
}