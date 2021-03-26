package com.wuwind.zrouter_compiler.utils;

/**
 * Created by wuhf on 2020/6/15.
 * Description ：
 */
public class StringUtils {
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}
