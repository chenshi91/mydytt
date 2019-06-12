package com.dytt.common.utils;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author chenshi
 */
public class StringUtil extends StringUtils {


    /**
     * 删除指定下标字符
     *
     * @param string
     * @param index
     * @return
     */
    public static String remove(String string, int index) {
        if (isNull(string)) {
            return null;
        }
        String[] strings = toStringArray(string);
        ArrayList<Object> list = toList(strings);
        list.remove(index);
        StringBuffer sb = new StringBuffer();
        list.stream().forEach(s -> sb.append(s));
        return sb.toString();
    }

    /**
     * 数组转list
     *
     * @param objects
     * @return
     */
    public static ArrayList<Object> toList(Object[] objects) {
        ArrayList<Object> list = Lists.newArrayList();
        Collections.addAll(list, objects);
        return list;
    }

    public static Object[] toStringArray(ArrayList<Object> list) {
        Object[] objects = new Object[list.size()];
        list.toArray(objects);
        return objects;
    }

    /**
     * 非空判断
     *
     * @param string
     * @return
     */
    public static Boolean isNull(String string) {
        if (!StringUtils.isEmpty(string) && !StringUtils.isEmpty(string.trim())) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 字符串转数组
     *
     * @param string
     * @return
     */
    public static String[] toStringArray(String string) {
        return string.split("");
    }

    /**
     * 数组转字符串
     *
     * @param strings
     * @return
     */
    public static String toString(String[] strings) {
        return org.apache.commons.lang.StringUtils.join(strings);
    }


}
