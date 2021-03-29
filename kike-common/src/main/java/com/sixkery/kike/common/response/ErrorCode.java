package com.sixkery.kike.common.response;

/**
 * 封装 API 的错误码
 *
 * @author sixkery
 * @date 2020/4/21
 * <p>
 */
public interface ErrorCode {
    /**
     * 获取状态码
     *
     * @return 状态码
     */
    int getCode();

    /**
     * 获取错误信息
     *
     * @return 错误信息
     */
    String getMessage();
}
