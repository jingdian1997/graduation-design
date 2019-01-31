package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.UserFocusDO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.VO.FocusVO;
import com.jd.graduation.service.UserFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("UserFocusServiceImpl")
public class UserFocusServiceImpl extends UserFocusService {
    @Autowired
    private BookServiceImpl bookService;

    public List<FocusVO> listByUid(Integer uid) {
        return baseMapper.selectByUid(uid);
    }

    public void focusOrDelete(Integer bid, Integer uid) throws Exception {
        QueryWrapper<UserFocusDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("bid", bid);

        int count = baseMapper.selectCount(wrapper);
        // 没有关注过，关注处理
        if (count == 0) {
            BookVO book = bookService.selectOne(bid);

            if (book == null || book.isDel()) {
                throw new Exception("关注失败，图书不存在或已下架");
            }

            UserFocusDO userFocusDO = new UserFocusDO();
            userFocusDO.setUid(uid);
            userFocusDO.setBid(bid);
            userFocusDO.setCreateTime(new Date());

            baseMapper.insert(userFocusDO);
        } else {
            // 否则取关
            baseMapper.delete(wrapper);
        }
    }
}