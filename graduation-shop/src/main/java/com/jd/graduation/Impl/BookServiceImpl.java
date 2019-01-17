package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.BookDO;
import com.jd.graduation.DO.CategoryDO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.service.BookService;
import com.jd.graduation.util.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("BookServiceImpl")
public class BookServiceImpl extends BookService {
    @Autowired
    private CategoryServiceImpl categoryService;

    public Page<BookVO> selectList(Page<BookVO> page, Integer cid, String query) {
        List<BookVO> bookVOList = null;

        if (query == null) {
            query = "";
        }

        if (cid != null){
            List<Integer> cids = categoryService.getAllCategoryIds(cid);
            String str = MyStringUtils.listToString(cids);
            if ("".equals(query)){
                bookVOList = baseMapper.getBooksByCategoryNotDel(page, str);
            } else {
                bookVOList = baseMapper.getBooksByQueryAndCategoryNotDel(page, query, str);
            }
        } else {
            if ("".equals(query)){
                bookVOList = baseMapper.getBooksNotDel(page);
            } else {
                bookVOList = baseMapper.getBooksByQueryNotDel(page, query);
            }
        }

        for (BookVO bookVO : bookVOList) {
            Integer oneCid = bookVO.getCid();
            List<CategoryDO> categoryDOList = categoryService.getAllCategories(oneCid);
            bookVO.setCategories(categoryDOList);
        }

        return page.setRecords(bookVOList);
    }

    public BookVO selectOne(Integer bookId) {
        BookVO bookVO = baseMapper.getOneBookNotDel(bookId);

        if (bookVO == null){
            return null;
        }

        Integer oneCid = bookVO.getCid();
        List<CategoryDO> categoryDOList = categoryService.getAllCategories(oneCid);
        bookVO.setCategories(categoryDOList);

        return bookVO;
    }

    public Map<String, Object> index(int page, int size) {
        Map<String, Object> result = new HashMap<>();
        Map<Integer, List<BookVO>> books = new HashMap<>();

        List<CategoryDO> categoryDOList = categoryService.getTopCategories();
        result.put("category", categoryDOList);

        for (CategoryDO c : categoryDOList) {
            Integer cid = c.getId();
            List<Integer> cids = categoryService.getAllCategoryIds(cid);
            String str = MyStringUtils.listToString(cids);

            List<BookVO> bookVOList = baseMapper.getBooksByCategoryNotDel(new Page<>(page, size), str);
            books.put(cid, bookVOList);
        }

        result.put("book", books);
        return result;
    }

    public double getRealPrice(Integer bid) {
        BookDO bookDO = baseMapper.selectById(bid);
        return bookDO.getDiscount() * bookDO.getPrice();
    }
}