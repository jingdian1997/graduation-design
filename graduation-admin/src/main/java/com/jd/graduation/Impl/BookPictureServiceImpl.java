package com.jd.graduation.Impl;

import com.jd.graduation.DO.BookPictureDO;
import com.jd.graduation.DTO.BookUpdatePictureDTO;
import com.jd.graduation.service.BookPictureService;
import org.springframework.stereotype.Service;

@Service("BookPictureServiceImpl")
public class BookPictureServiceImpl extends BookPictureService {
    public void insert(BookPictureDO pictureDO){
        baseMapper.insert(pictureDO);
    }


    public void updatePicture(BookUpdatePictureDTO dto) throws Exception {
        BookPictureDO book = baseMapper.selectById(dto.getId());

        if (book != null) {
            book.setPicture(dto.getPicture());
            baseMapper.updateById(book);
        } else {
            throw new Exception("不存在的图书信息");
        }
    }
}