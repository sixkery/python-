package com.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author sixkery
 * @date 2020/5/16
 * <p>
 * 字符串操作工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StringUtil {

    public static boolean isNotEmpty(String str) {
        return !org.springframework.util.StringUtils.isEmpty(str);

    }
}
