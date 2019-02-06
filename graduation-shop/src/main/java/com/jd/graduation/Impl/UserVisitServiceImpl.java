package com.jd.graduation.Impl;

import com.jd.graduation.DO.UserVisitDO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.VO.VisitVO;
import com.jd.graduation.service.UserVisitService;
import com.jd.graduation.util.MyStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("UserVisitServiceImpl")
public class UserVisitServiceImpl extends UserVisitService {
    @Autowired
    private BookServiceImpl bookService;

    public List<VisitVO> listByUid(Integer uid) {
        return baseMapper.selectByUid(uid);
    }

    public void create(Integer uid, Integer bid) throws Exception {
        BookVO book = bookService.selectOne(bid);

        if (book == null) {
            throw new Exception("访问失败，图书不存在");
        }
        if (book.isDel()) {
            return;
        }

        UserVisitDO userVisitDO = new UserVisitDO();
        userVisitDO.setBid(bid);
        userVisitDO.setUid(uid);
        userVisitDO.setCreateTime(new Date());
        userVisitDO.setDel(false);

        baseMapper.insert(userVisitDO);
    }

    public void delete(List<Integer> ids, Integer uid) {
        String str = MyStringUtils.listToString(ids);
        baseMapper.updateDel(uid, str);
    }
}