package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.BookDO;
import com.jd.graduation.DO.CategoryDO;
import com.jd.graduation.DTO.BookCreateDTO;
import com.jd.graduation.DTO.BookUpdateDTO;
import com.jd.graduation.DTO.StockCreateDTO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.service.BookService;
import com.jd.graduation.util.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("BookServiceImpl")
public class BookServiceImpl extends BookService {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private StockServiceImpl stockService;

    public Integer countBookByCategoryAndDel(Integer cid){
        QueryWrapper<BookDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid);
        queryWrapper.eq("del", 0);

        return baseMapper.selectCount(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
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

        StockCreateDTO stockCreateDTO = new StockCreateDTO();
        stockCreateDTO.setBid(book.getId());
        stockCreateDTO.setStock(0);

        stockService.insert(stockCreateDTO);
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

    public Page<BookVO> selectList(Page<BookVO> page, Integer cid, String query) {
        List<BookVO> bookVOList = null;

        if (query == null) {
            query = "";
        }

        if (cid != null){
            List<Integer> cids = categoryService.getAllCategoryIds(cid);
            String str = MyStringUtils.listToString(cids);
            bookVOList = baseMapper.getBooksByCategory(page, query, str);
        } else {
            bookVOList = baseMapper.getBooks(page, query);
        }

        for (BookVO bookVO : bookVOList) {
            Integer oneCid = bookVO.getCid();
            List<CategoryDO> categoryDOList = categoryService.getAllCategories(oneCid);
            bookVO.setCategories(categoryDOList);
        }

        return page.setRecords(bookVOList);
    }

    public BookVO selectOne(Integer bookId) {
        BookVO bookVO = baseMapper.getOneBook(bookId);

        Integer oneCid = bookVO.getCid();
        List<CategoryDO> categoryDOList = categoryService.getAllCategories(oneCid);
        bookVO.setCategories(categoryDOList);

        return bookVO;
    }
}