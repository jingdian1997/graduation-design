package com.jd.graduation.util;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReturnMap {
    private int code;
    private String message;
    private Object data;

    public static ReturnMap ok(Object data) {
        return new ReturnMap(200, null, data);
    }

    public static ReturnMap error(String message){
        return new ReturnMap(500, message, null);
    }
}