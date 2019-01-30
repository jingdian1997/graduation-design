package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.BookDO;
import com.jd.graduation.DO.BookPictureDO;
import com.jd.graduation.DO.CategoryDO;
import com.jd.graduation.DO.StockDO;
import com.jd.graduation.DTO.BookCreateDTO;
import com.jd.graduation.DTO.BookUpdateDTO;
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
    @Autowired
    private BookPictureServiceImpl bookPictureService;

    public Integer countBookByCategoryAndDel(Integer cid) {
        QueryWrapper<BookDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid);
        queryWrapper.eq("del", 0);

        return baseMapper.selectCount(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(BookCreateDTO dto) throws Exception {
        BookDO book = new BookDO();

        // 设置图书主表
        book.setISBN(dto.getISBN());
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setPublishDate(dto.getPublishDate());
        book.setDescription(dto.getDescription());

        book.setCid(dto.getCid());
        book.setDel(false);
        baseMapper.insert(book);

        // 设置图片参数
        BookPictureDO bookPictureDO = new BookPictureDO();
        bookPictureDO.setId(book.getId());
        bookPictureDO.setPicture(dto.getPicture());
        bookPictureService.insert(bookPictureDO);

        //设置库存
        StockDO stockDO = new StockDO();
        stockDO.setId(book.getId());
        stockDO.setBid(book.getId());
        stockDO.setStock(1000);
        stockService.insert(stockDO);
    }

    public void update(BookUpdateDTO dto) throws Exception {
        BookDO book = baseMapper.selectById(dto.getId());

        if (book != null) {
            book.setDescription(dto.getDescription());
            book.setPrice(dto.getPrice());
            book.setDel(false);
            baseMapper.updateById(book);
        } else {
            throw new Exception("不存在的图书信息");
        }
    }

    public void pullOff(Integer bookId) throws Exception {
        BookDO book = baseMapper.selectById(bookId);

        if (book == null) {
            throw new Exception("不存在的图书信息");
        }

        book.setDel(true);
        baseMapper.updateById(book);
    }

    public Page<BookVO> selectList(Page<BookVO> page, Integer cid, String query) throws Exception {
        List<BookVO> bookVOList = null;

        if (query == null) {
            query = "";
        }

        if (cid != null){
            List<Integer> cids = categoryService.getAllCategoryIds(cid);
            String str = MyStringUtils.listToString(cids);

            if ("".equals(query)){
                bookVOList = baseMapper.getBooksByCategory(page, str);
            } else {
                bookVOList = baseMapper.getBooksByCategoryAndQuery(page, query, str);
            }
        } else {
            if ("".equals(query)) {
                bookVOList = baseMapper.getBooks(page);
            } else {
                bookVOList = baseMapper.getBooksByQuery(page, query);
            }
        }

        for (BookVO bookVO : bookVOList) {
            Integer oneCid = bookVO.getCid();
            List<CategoryDO> categoryDOList = categoryService.getAllCategories(oneCid);
            bookVO.setCategories(categoryDOList);
        }

        return page.setRecords(bookVOList);
    }

    public BookVO selectOne(Integer bookId) throws Exception {
        BookVO bookVO = baseMapper.getOneBook(bookId);

        if (bookVO == null){
            return null;
        }

        Integer oneCid = bookVO.getCid();
        List<CategoryDO> categoryDOList = categoryService.getAllCategories(oneCid);
        bookVO.setCategories(categoryDOList);

        return bookVO;
    }
}