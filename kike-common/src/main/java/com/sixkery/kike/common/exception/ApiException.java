package com.sixkery.kike.common.exception;


import com.sixkery.kike.common.response.ErrorCode;

/**
 * 自定义API异常
 *
 * @author sixkery
 * @date 2020/4/23
 */
public class ApiException extends RuntimeException {

    private ErrorCode errorCode;

    private Object data;

    public ApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode, Object data) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.data = data;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }


    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Object getData() {
        return this.data;
    }
}
