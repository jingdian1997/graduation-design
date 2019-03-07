package com.jd.graduation.Impl;

import com.jd.graduation.DO.CartDO;
import com.jd.graduation.DTO.CartChangeAmountDTO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.VO.CartVO;
import com.jd.graduation.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("CartServiceImpl")
public class CartServiceImpl extends CartService {
    @Autowired
    private BookServiceImpl bookService;

    public void addToCart(Integer uid, Integer bid) throws Exception {
        BookVO bookVO = bookService.selectOne(bid);

        if (bookVO == null){
            throw new Exception("错误的图书");
        }

        CartDO cartDO = new CartDO();
        cartDO.setUid(uid);
        cartDO.setBid(bid);
        cartDO.setAmount(1);
        cartDO.setCreateTime(new Date());

        save(cartDO);
    }

    public void delete(Integer id, Integer uid) throws Exception {
        CartDO cartDO = baseMapper.selectById(id);
        if (cartDO == null || !cartDO.getUid().equals(uid)){
            throw new Exception("你只能操作你的购物车");
        }

        removeById(id);
    }

    public List<CartVO> getAll(Integer uid) {
        return baseMapper.selectByUid(uid);
    }

    public void changeAmount(CartChangeAmountDTO dto, Integer uid) throws Exception {
        CartDO cartDO = baseMapper.selectById(dto.getId());
        if (cartDO == null || !cartDO.getUid().equals(uid)){
            throw new Exception("你只能操作你的购物车");
        }

        cartDO.setAmount(dto.getAmount());

        updateById(cartDO);
    }
}