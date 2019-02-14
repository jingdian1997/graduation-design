package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.graduation.DO.BookDO;
import com.jd.graduation.DO.CartDO;
import com.jd.graduation.DO.OrderDetailDO;
import com.jd.graduation.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("OrderDetailServiceImpl")
public class OrderDetailServiceImpl extends OrderDetailService {
    @Autowired
    private CartServiceImpl cartService;
    @Autowired
    private BookServiceImpl bookService;
    @Autowired
    private StockServiceImpl stockService;

    @Transactional(rollbackFor = Exception.class)
    public double createList(List<Integer> cartIds, Integer uid, Integer oid) throws Exception {
        if (cartIds.size() == 0) {
            throw new Exception("空订单不可提交！");
        }

        List<CartDO> cartDOS = cartService.getInIds(cartIds, uid);
        double price = 0.0;

        for (CartDO one : cartDOS) {
            OrderDetailDO orderDetailDO = new OrderDetailDO();

            Integer bid = one.getBid();
            Map<String, Object> realMap = bookService.getRealBook(bid);
            double singlePrice = (double) realMap.get("real");
            BookDO bookDO = (BookDO) realMap.get("book");

            Integer amount = one.getAmount();

            boolean result = stockService.reduceStock(amount, bid);
            if (!result){
                throw new Exception(bid + "库存不足，创单失败");
            }

            double total = singlePrice * amount;
            price += total;

            orderDetailDO.setBid(bid);
            orderDetailDO.setBname(bookDO.getName());
            orderDetailDO.setAmount(amount);
            orderDetailDO.setPrice(singlePrice);
            orderDetailDO.setTotal(total);
            orderDetailDO.setOid(oid);
            orderDetailDO.setUid(uid);
            orderDetailDO.setFlag(0);
            baseMapper.insert(orderDetailDO);

            //删除购购物车
            cartService.delete(one.getId(), uid);
        }

        return price;
    }

    public void cancelList(Integer oid){
        List<OrderDetailDO> orderDetailDOS = getDetails(oid);

        //释放库存
        for (OrderDetailDO one : orderDetailDOS) {
            stockService.addStock(one.getAmount(), one.getBid());
        }

        baseMapper.updateFlagByOid(oid, -1);
    }

    public void payList(Integer oid) {
        baseMapper.updateFlagByOid(oid, 1);
    }

    public void completeList(Integer oid) {
        baseMapper.updateFlagByOid(oid, 4);
    }

    public void deleteList(Integer oid) {
        QueryWrapper<OrderDetailDO> wrapper = new QueryWrapper<>();
        wrapper.eq("oid", oid);
        baseMapper.delete(wrapper);
    }

    private List<OrderDetailDO> getDetails(Integer oid){
        QueryWrapper<OrderDetailDO> wrapper = new QueryWrapper<>();
        wrapper.eq("oid", oid);
        return baseMapper.selectList(wrapper);
    }

    public OrderDetailDO getOne(Integer id) {
        return baseMapper.selectById(id);
    }

    public List<OrderDetailDO> getByOid(Integer oid) {
        QueryWrapper<OrderDetailDO> wrapper = new QueryWrapper<>();
        wrapper.eq("oid", oid);
        return baseMapper.selectList(wrapper);
    }

    public boolean checkBought(Integer odid, Integer bid) {
        OrderDetailDO orderDetailDO = baseMapper.selectById(odid);
        //用户必须已经完成相关订单才可以评论
        return orderDetailDO.getBid().equals(bid) && orderDetailDO.getFlag() == 4;
    }

    @Transactional(rollbackFor = Exception.class)
    public void setCommented(Integer odid) {
        OrderDetailDO orderDetailDO = baseMapper.selectById(odid);
        orderDetailDO.setIfComment(true);
        baseMapper.updateById(orderDetailDO);
    }
}