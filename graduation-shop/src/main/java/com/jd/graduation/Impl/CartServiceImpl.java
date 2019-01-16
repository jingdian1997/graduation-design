package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.CartDO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("CartServiceImpl")
public class CartServiceImpl extends CartService {
    @Autowired
    private BookServiceImpl bookService;

    public String addToCart(Integer uid, Integer bid) {
        BookVO bookVO = bookService.selectOne(bid);

        if (bookVO == null){
            return "错误的图书";
        }

        CartDO cartDO = new CartDO();
        cartDO.setUid(uid);
        cartDO.setBid(bid);
        cartDO.setPrice(bookVO.getPrice());
        cartDO.setCreateTime(new Date());

        baseMapper.insert(cartDO);
        return null;
    }

    public void deleteFromCart(Integer id) {
        baseMapper.deleteById(id);
    }

    public List<CartDO> getInIds(List<Integer> ids, Integer uid) {
        return baseMapper.getInIds(ids, uid);
    }

    public List<CartDO> getAll(Integer uid) {
        QueryWrapper<CartDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        return baseMapper.selectList(wrapper);
    }
}