package com.jd.graduation.Impl;

import com.jd.graduation.VO.StaticAmountVO;
import com.jd.graduation.VO.StaticPriceVO;
import com.jd.graduation.VO.StaticsMonthVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("StaticServiceImpl")
public class StaticServiceImpl {
    private static final int STATIC_NUMBER = 5;

    @Autowired
    private OrderDetailServiceImpl orderDetailService;
    @Autowired
    private OrderServiceImpl orderService;

    public Map<String, Object> getPriceStatics() {
        Map<String, Object> map = new HashMap<>();
        List<StaticPriceVO> dataValue = new ArrayList<>();
        Double total = 0.0;
        Double otherTotal = 0.0;

        List<StaticPriceVO> statics = orderDetailService.getPriceStatics();

        for (int i = 0; i < statics.size(); ++i) {
            StaticPriceVO vo = statics.get(i);
            total += vo.getValue();
            if (i < STATIC_NUMBER) {
                dataValue.add(vo);
            } else {
                otherTotal += vo.getValue();
            }
        }

        StaticPriceVO other = new StaticPriceVO();
        other.setName("其它图书");
        other.setValue(otherTotal);
        dataValue.add(other);

        map.put("total", total);
        map.put("pie", dataValue);

        return map;
    }

    public Map<String, Object> getAmountStatics() {
        Map<String, Object> map = new HashMap<>();
        List<StaticAmountVO> dataValue = new ArrayList<>();
        Integer total = 0;
        Integer otherTotal = 0;

        List<StaticAmountVO> statics = orderDetailService.getAmountStatics();

        for (int i = 0; i < statics.size(); ++i) {
            StaticAmountVO vo = statics.get(i);
            total += vo.getValue();
            if (i < STATIC_NUMBER) {
                dataValue.add(vo);
            } else {
                otherTotal += vo.getValue();
            }
        }

        StaticAmountVO other = new StaticAmountVO();
        other.setName("其它图书");
        other.setValue(otherTotal);
        dataValue.add(other);

        map.put("total", total);
        map.put("pie", dataValue);

        return map;
    }

    public Map<String, Object> getMonth() {
        Map<String, Object> map = new HashMap<>();

        List<StaticsMonthVO> monthVOS = orderService.getStaticsByMonth();;
        Double total = orderService.allPay();

        map.put("total", total);
        map.put("month", monthVOS);

        return map;
    }

    public List<Double> getMonth12(String year) {
        return orderService.getMonth12(year);
    }
}