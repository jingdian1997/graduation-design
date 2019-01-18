package com.jd.graduation.DO;

import lombok.Getter;

@Getter
public enum SysConfigKey {
    FREIGHT_FREE_PRICE("FREIGHT_FREE_PRICE"),
    FREIGHT("FREIGHT");

    private String key;

    private SysConfigKey(String key){
        this.key = key;
    }
}