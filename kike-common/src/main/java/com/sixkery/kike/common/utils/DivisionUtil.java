package com.common.utils;

import java.math.BigDecimal;

/**
 * @author sixkery
 * @date 2020/4/16
 * 除法计算工具类
 */
public class DivisionUtil {
    /**
     * @param a 单数
     * @param b 总数
     * @return a / b    计算百分比
     */
    public static String calculate(int a, int b) {
        BigDecimal aa = BigDecimal.valueOf(a);
        BigDecimal bb = BigDecimal.valueOf(b);

        return bb == null ? "0.00%" : bb.compareTo(new BigDecimal(0)) == 0 ? "0%" : aa == null ? "0.00%" :
                aa.multiply(new BigDecimal(100)).divide(bb, 2, BigDecimal.ROUND_HALF_UP) + "%";

    }

    public static void main(String[] args) {
        String calculate = calculate(190000, 190000);
        System.out.println("calculate = " + calculate);
    }

}
