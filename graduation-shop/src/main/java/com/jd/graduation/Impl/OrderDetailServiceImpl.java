package com.jd.graduation.Impl;

import com.jd.graduation.DO.CartDO;
import com.jd.graduation.DO.OrderDetailDO;
import com.jd.graduation.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("OrderDetailServiceImpl")
public class OrderDetailServiceImpl extends OrderDetailService {
    @Autowired
    private CartServiceImpl cartService;
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private StockServiceImpl stockService;

    @Transactional(rollbackFor = Exception.class)
    public double createList(List<Integer> cartIds, Integer uid, Integer oid){
        List<CartDO> cartDOS = cartService.getInIds(cartIds, uid);
        double price = 0.0;

        for (CartDO one : cartDOS) {
            OrderDetailDO orderDetailDO = new OrderDetailDO();

            Integer bid = one.getBid();
            double singlePrice = bookService.getRealPrice(bid);
            Integer amount = one.getAmount();

            //TODO:库存不足的处理
            boolean result = stockService.reduceStock(amount, bid);
            if (!result){
                continue;
            }

            double total = singlePrice * amount;
            price += total;
            orderDetailDO.setBid(bid);
            orderDetailDO.setAmount(amount);
            orderDetailDO.setPrice(singlePrice);
            orderDetailDO.setTotal(total);
            orderDetailDO.setOid(oid);
            baseMapper.insert(orderDetailDO);

            //删除购购物车
            cartService.delete(one.getId(), uid);
        }

        return price;
    }
}