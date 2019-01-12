package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.BookDO;
import com.jd.graduation.service.BookService;
import org.springframework.stereotype.Service;

@Service("BookServiceImpl")
public class BookServiceImpl extends BookService {
    public Integer countBookByCategory2AndDel(Integer c2id){
        QueryWrapper<BookDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("c2id", c2id);
        queryWrapper.eq("del", 0);

        return baseMapper.selectCount(queryWrapper);
    }

    public Integer countBookByCategoryAndDel(Integer cid){
        QueryWrapper<BookDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid);
        queryWrapper.eq("del", 0);

        return baseMapper.selectCount(queryWrapper);
    }

//    public void insert(BookCreateModel model) {
//        Book book = new Book();
//
//        book.setName(model.getName());
//        book.setAuthor(model.getAuthor());
//        book.setPublisher(model.getPublisher());
//        book.setPublishDate(model.getPublishDate());
//        book.setDescription(model.getDescription());
//        book.setSale(model.getSale());
//        book.setPicture(model.getPicture());
//        book.setCategoryId(model.getCategoryId());
//        book.setDel(false);
//        book.setStock(0);
//
//        baseMapper.insert(book);
//    }
//
//    public void update(BookUpdateModel model) {
//        Book book = baseMapper.selectById(model.getId());
//        //set fields,exclude id and stock
//        if (book != null) {
//
//            book.setDel(false);
//            book.setName(model.getName());
//            book.setAuthor(model.getAuthor());
//            book.setPublisher(model.getPublisher());
//            book.setPublishDate(model.getPublishDate());
//            book.setPicture(model.getPicture());
//            book.setDescription(model.getDescription());
//            book.setSale(model.getSale());
//            book.setCategoryId(model.getCategoryId());
//
//            baseMapper.updateById(book);
//        }
//    }
//
//    public void pullOff(int bookId) {
//        Book book = baseMapper.selectById(bookId);
//
//        book.setDel(true);
//        baseMapper.updateById(book);
//    }
//
//    public Page<BookCategoryVO> selectList(Page<BookCategoryVO> page, Integer categoryId, String query) {
//        if (query == null) {
//            query = "";
//        }
//
//        if (categoryId == null){
//            return page.setRecords(baseMapper.getBooks(page, query));
//        }
//        return page.setRecords(baseMapper.getBooksByCategory(page, query, categoryId));
//    }
//
//    public BookCategoryVO selectOne(Integer bookId) {
//        return baseMapper.getOneBook(bookId);
//    }
}