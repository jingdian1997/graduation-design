package com.jd.graduation.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.graduation.DO.CommentDO;
import com.jd.graduation.DTO.CommentReplyDTO;
import com.jd.graduation.VO.CommentVO;
import com.jd.graduation.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("CommentServiceImpl")
public class CommentServiceImpl extends CommentService {
    public void deleteComment(Integer id) throws Exception {
        CommentDO commentDO = baseMapper.selectById(id);
        if (commentDO == null){
            return;
        }

        baseMapper.deleteById(id);
    }

    public Page<CommentVO> getList(Page<CommentVO> page) {
        List<CommentVO> list = baseMapper.get(page);
        return page.setRecords(list);
    }

    public Page<CommentVO> getListByBid(Integer bid, Page<CommentVO> page) {
        List<CommentVO> list = baseMapper.getByBid(bid, page);
        return page.setRecords(list);
    }

    public void replyComment(CommentReplyDTO dto) throws Exception {
        CommentDO commentDO = baseMapper.selectById(dto.getId());
        if (commentDO == null) {
            throw new Exception("该评论不存在");
        }

        commentDO.setReply(dto.getReply());
        commentDO.setReplyTime(new Date());

        baseMapper.updateById(commentDO);
    }
}