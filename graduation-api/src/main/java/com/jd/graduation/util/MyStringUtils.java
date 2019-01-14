package com.jd.graduation.util;

import java.util.List;

public class MyStringUtils {
    public static String listToString(List<Integer> list) {
        String str = list.toString();
        str = str.replace("[", "");
        str = str.replace("]", "");
        str = str.replace(" ", "");
        return str;
    }
}