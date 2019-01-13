package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.BookDO;
import com.jd.graduation.DTO.BookCreateDTO;
import com.jd.graduation.DTO.BookUpdateDTO;
import com.jd.graduation.VO.BookVO;
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

    public void insert(BookCreateDTO dto) {
        BookDO book = new BookDO();

        book.setISBN(dto.getISBN());
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setPublishDate(dto.getPublishDate());
        book.setDescription(dto.getDescription());
        book.setPrice(dto.getPrice());
        book.setPicture(dto.getPicture());

        book.setCid(dto.getCid());
        book.setC2id(dto.getC2id());
        book.setDel(false);

        baseMapper.insert(book);
    }

    public void update(BookUpdateDTO dto) {
        BookDO book = baseMapper.selectById(dto.getId());
        //set fields,exclude id and isbn
        if (book != null) {
//            book.setName(dto.getName());
//            book.setAuthor(dto.getAuthor());
//            book.setPublisher(dto.getPublisher());
//            book.setPublishDate(dto.getPublishDate());
            book.setPicture(dto.getPicture());
            book.setDescription(dto.getDescription());
            book.setPrice(dto.getPrice());

            book.setDel(false);
            baseMapper.updateById(book);
        }
    }

    public void pullOff(Integer bookId) {
        BookDO book = baseMapper.selectById(bookId);

        book.setDel(true);
        baseMapper.updateById(book);
    }

    public Page<BookVO> selectList(Page<BookVO> page, Integer cid, Integer c2id, String query) {
        if (query == null) {
            query = "";
        }

        if (cid != null){
            if (c2id != null){
                return page.setRecords(baseMapper.getBooksByCategory2(page, query, cid, c2id));
            } else {
                return page.setRecords(baseMapper.getBooksByCategory(page, query, cid));
            }
        }

        return page.setRecords(baseMapper.getBooks(page, query));
    }

    public BookVO selectOne(Integer bookId) {
        return baseMapper.getOneBook(bookId);
    }
}