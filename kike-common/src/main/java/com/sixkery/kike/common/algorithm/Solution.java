package com.sixkery.kike.common.algorithm;

/**
 * @author sixkery
 * @date 2021/6/17
 */
public class Solution {

    /**
     * 反转字符串
     *
     * @param str string字符串
     * @return string字符串
     */
    public static String solve(String str) {
        // write code here
        String reverse = "";
        char[] array = str.toCharArray();
        for (int i = array.length - 1; i >= 0; i--) {
            reverse = reverse + array[i];
        }
        return reverse;

    }

    /**
     * 反转字符串
     *
     * @param str string字符串
     * @return string字符串
     */
    public static String solve1(String str) {
        // write code here
        StringBuilder stringBuilder = new StringBuilder(str);
        return stringBuilder.reverse().toString();

    }

    public static String solve2(String str) {
        // write code here

        int length = str.length();
        String reverse = "";
        for (int i = 0; i < length; i++) {
            reverse = str.charAt(i) + reverse;

        }
        return reverse;

    }

    public static String solve3(String str) {
        int length = str.length();
        if (length <= 1) {
            return str;
        }
        String left = str.substring(0, length / 2);
        String right = str.substring(length / 2, length);
        return solve3(right) + solve3(left);
    }

    public static void main(String[] args) {
        String s = solve3("123");
        System.out.println("s = " + s);


    }
}
