package com.jd.graduation.controller;

import com.jd.graduation.DTO.StockAddDTO;
import com.jd.graduation.Impl.StockServiceImpl;
import com.jd.graduation.util.ReturnMap;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/stock")
@Api(description = "库存模拟")
public class StockController extends BaseController{
    @Autowired
    private StockServiceImpl stockService;

    @PostMapping("/add")
    public ReturnMap add(@RequestBody @Valid StockAddDTO dto) {
        stockService.addStock(dto.getNumber(), dto.getBid());
        return ReturnMap.ok(null);
    }
}