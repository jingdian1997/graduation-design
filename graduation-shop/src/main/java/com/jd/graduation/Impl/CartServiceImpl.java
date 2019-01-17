package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.CartDO;
import com.jd.graduation.DTO.CartChangeAmountDTO;
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
        cartDO.setAmount(1);
        cartDO.setCreateTime(new Date());

        baseMapper.insert(cartDO);
        return null;
    }

    public String delete(Integer id, Integer uid) {
        CartDO cartDO = baseMapper.selectById(id);
        if (cartDO == null || !cartDO.getUid().equals(uid)){
            return "不是你的购物车";
        }

        baseMapper.deleteById(id);
        return null;
    }

    public List<CartDO> getInIds(List<Integer> ids, Integer uid) {
        return baseMapper.getInIds(ids, uid);
    }

    public List<CartDO> getAll(Integer uid) {
        QueryWrapper<CartDO> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        return baseMapper.selectList(wrapper);
    }

    public String changeAmount(CartChangeAmountDTO dto, Integer uid) {
        CartDO cartDO = baseMapper.selectById(dto.getId());
        if (cartDO == null || !cartDO.getUid().equals(uid)){
            return "不是你的购物车";
        }

        cartDO.setAmount(dto.getAmount());
        baseMapper.updateById(cartDO);
        return null;
    }
}