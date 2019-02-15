package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.CommentDO;
import com.jd.graduation.DTO.CommentCreateDTO;
import com.jd.graduation.VO.BookVO;
import com.jd.graduation.VO.CommentVO;
import com.jd.graduation.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("CommentServiceImpl")
public class CommentServiceImpl extends CommentService {
    @Autowired
    private OrderDetailServiceImpl orderDetailService;

    public void makeComment(CommentCreateDTO dto, Integer uid) throws Exception {
        if (!orderDetailService.checkBought(dto.getOdid(), dto.getBid())){
            throw new Exception("你没有买过该商品，不可评价");
        }

        CommentDO commentDO = new CommentDO();

        commentDO.setUid(uid);
        commentDO.setBid(dto.getBid());
        commentDO.setContent(dto.getContent());
        commentDO.setScore(dto.getScore());
        commentDO.setTime(new Date());

        baseMapper.insert(commentDO);
        orderDetailService.setCommented(dto.getOdid());
    }


    public void deleteComment(Integer id, Integer uid) throws Exception {
        CommentDO commentDO = baseMapper.selectById(id);
        if (commentDO == null || !commentDO.getUid().equals(uid)){
            throw new Exception("不是你的评论，不可删除");
        }

        baseMapper.deleteById(id);
    }

    public Page<CommentVO> getListByUid(Integer uid, Page<CommentVO> page) {
        List<CommentVO> list = baseMapper.getByUid(uid, page);
        return page.setRecords(list);
    }

    public Page<CommentVO> getListByBid(Integer bid, Page<CommentVO> page) {
        List<CommentVO> list = baseMapper.getByBid(bid, page);
        return page.setRecords(list);
    }

    public List<BookVO> getHighestScore(int size) {
        return baseMapper.getHighestScore(size);
    }
}