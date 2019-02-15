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
    @Autowired
    private UserVisitServiceImpl userVisitService;
    @Autowired
    private UserFocusServiceImpl userFocusService;
    @Autowired
    private OrderDetailServiceImpl orderDetailService;
    @Autowired
    private CommentServiceImpl commentService;

    public Page<BookVO> selectList(Page<BookVO> page, Integer cid, String query) {
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
            if ("".equals(query)){
                bookVOList = baseMapper.getBooks(page);
            } else {
                bookVOList = baseMapper.getBooksByQuery(page, query);
            }
        }

        // 图书浏览时不需要设置类目路径
//        for (BookVO bookVO : bookVOList) {
//            Integer oneCid = bookVO.getCid();
//            List<CategoryDO> categoryDOList = categoryService.getAllCategories(oneCid);
//            bookVO.setCategories(categoryDOList);
//        }

        return page.setRecords(bookVOList);
    }

    public BookVO selectOne(Integer bookId) {
        BookVO bookVO = baseMapper.getOneBook(bookId);

        if (bookVO == null){
            return null;
        }

        Integer oneCid = bookVO.getCid();
        List<CategoryDO> categoryDOList = categoryService.getAllCategories(oneCid);
        bookVO.setCategories(categoryDOList);

        return bookVO;
    }

    public Map<String, List<BookVO>> index(int size) {
        Map<String, List<BookVO>> map = new HashMap<>();

        //新书榜（news），人气榜（visit），关注榜（focus），热销榜（detail），好评榜（comment）
        List<BookVO> newest = news(size);
        List<BookVO> visits = userVisitService.getMostVisited(size);
        List<BookVO> focus = userFocusService.getMostFocused(size);
        List<BookVO> sell = orderDetailService.getMostSold(size);
        List<BookVO> score = commentService.getHighestScore(size);

        map.put("new", newest);
        map.put("visit", visits);
        map.put("sell", sell);
        map.put("focus", focus);
        map.put("score", score);

        return map;
    }

    public Page<BookVO> getBooksByCategory(Integer cid, Page<BookVO> page) {
        List<Integer> cids = categoryService.getAllCategoryIds(cid);
        String str = MyStringUtils.listToString(cids);

        List<BookVO> bookVOList = baseMapper.getBooksByCategoryNotDel(page, str);
        return page.setRecords(bookVOList);
    }

    public Map<String, Object> getRealBook(Integer bid) {
        Map<String, Object> map = new HashMap<>();
        BookDO bookDO = baseMapper.selectById(bid);

        map.put("book", bookDO);
        map.put("real", 1.00 * bookDO.getPrice());

        return map;
    }

    private List<BookVO> news(int size) {
        return baseMapper.getNewBooks(size);
    }
}