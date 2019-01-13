package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.BookDO;
import com.jd.graduation.DO.CategoryDO;
import com.jd.graduation.DTO.BookCreateDTO;
import com.jd.graduation.DTO.BookUpdateDTO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("BookServiceImpl")
public class BookServiceImpl extends BookService {
    @Autowired
    private CategoryServiceImpl categoryService;

    public Integer countBookByCategoryAndDel(Integer cid){
        QueryWrapper<BookDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid);
        queryWrapper.eq("del", 0);

        return baseMapper.selectCount(queryWrapper);
    }

    public void insert(BookCreateDTO dto) {
        BookDO book = new BookDO();

        book.setISBN(dto.getISBN());
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setPublishDate(dto.getPublishDate());
        book.setDescription(dto.getDescription());
        book.setPrice(dto.getPrice());
        book.setDiscount(dto.getDiscount());
        book.setPicture(dto.getPicture());

        book.setCid(dto.getCid());
        book.setDel(false);

        baseMapper.insert(book);
    }

    public void update(BookUpdateDTO dto) {
        BookDO book = baseMapper.selectById(dto.getId());
        //set fields,exclude id and isbn
        if (book != null) {
            book.setPicture(dto.getPicture());
            book.setDescription(dto.getDescription());
            book.setPrice(dto.getPrice());
            book.setDiscount(dto.getDiscount());

            book.setDel(false);
            baseMapper.updateById(book);
        }
    }

    public void pullOff(Integer bookId) {
        BookDO book = baseMapper.selectById(bookId);

        book.setDel(true);
        baseMapper.updateById(book);
    }

    public Page<BookDO> selectList(Page<BookDO> page, Integer cid, String query) {
        if (query == null) {
            query = "";
        }

        if (cid != null){
            Map<Integer, CategoryDO> map = categoryService.getMap();
            return page.setRecords(baseMapper.getBooksByCategory(page, query, cid));
        }

        return page.setRecords(baseMapper.getBooks(page, query));
    }

    public BookVO selectOne(Integer bookId) {
//        return baseMapper.getOneBook(bookId);
        return null;
    }
}