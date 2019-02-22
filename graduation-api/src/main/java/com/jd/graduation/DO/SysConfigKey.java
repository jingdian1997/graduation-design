package com.jd.graduation.DO;

import lombok.Getter;

@Getter
public enum SysConfigKey {
    FREIGHT_FREE_PRICE("FREIGHT_FREE_PRICE"),
    FREIGHT("FREIGHT"),
    CATEGORY_VERSION("CATEGORY_VERSION"),
    CATEGORY_MAX_LEVEL("CATEGORY_MAX_LEVEL"),
    CATEGORY_LEVEL("CATEGORY_LEVEL");

    private String key;

    private SysConfigKey(String key){
        this.key = key;
    }
}