package com.jd.graduation.Impl;

import com.jd.graduation.VO.StaticAmountVO;
import com.jd.graduation.VO.StaticPriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("StaticServiceImpl")
public class StaticServiceImpl {
    private static final int STATIC_NUMBER = 5;

    @Autowired
    private OrderDetailServiceImpl orderDetailService;

    public List<StaticPriceVO> getPriceStatics() {
        List<StaticPriceVO> dataValue = new ArrayList<>();

        List<StaticPriceVO> statics = orderDetailService.getPriceStatics();
        Double total = 0.0;

        for (int i = 0; i < statics.size(); ++i) {
            if (i < STATIC_NUMBER) {
                dataValue.add(statics.get(i));
            } else {
                total += statics.get(i).getValue();
            }
        }

        StaticPriceVO other = new StaticPriceVO();
        other.setName("其它图书");
        other.setValue(total);
        dataValue.add(other);

        return dataValue;
    }

    public List<StaticAmountVO> getAmountStatics() {
        List<StaticAmountVO> dataValue = new ArrayList<>();

        List<StaticAmountVO> statics = orderDetailService.getAmountStatics();
        Integer total = 0;

        for (int i = 0; i < statics.size(); ++i) {
            if (i < STATIC_NUMBER) {
                dataValue.add(statics.get(i));
            } else {
                total += statics.get(i).getValue();
            }
        }

        StaticAmountVO other = new StaticAmountVO();
        other.setName("其它图书");
        other.setValue(total);
        dataValue.add(other);

        return dataValue;
    }
}