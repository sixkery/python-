package com.common.utils;

import cn.hutool.core.convert.Convert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @author liugang
 * @date 2020/5/15
 * <p>
 * 集合工具类
 */
public class CollectionUtil extends CollectionUtils {

    /**
     * 验证集合是否为空
     *
     * @param collection 集合
     * @return boolean
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * 验证集合是否不为空
     *
     * @param collection 集合
     * @return boolean
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 验证 map 是否为空
     *
     * @param map map
     * @return boolean
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * 验证 map 是否不为空
     *
     * @param map map
     * @return boolean
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static void main(String[] args) {
        String a = "q";
        Integer integer = Convert.toInt(a, 0);
        System.out.println("integer = " + integer);
    }
}