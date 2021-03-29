package com.common.response;

import com.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理
 *
 * @author sixkery
 * @date 2020/4/23
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 系统内部错误捕获的异常
     *
     * @param e 异常
     * @return ApiResponses
     */
    @ExceptionHandler({Exception.class})
    public ApiResponses<String> uniteExceptionHandler(Exception e) {
        log.error("系统异常", e);
        return ApiResponses.failed(e.getMessage());
    }

    /**
     * 业务异常
     *
     * @param e 异常信息
     * @return 返回 json 格式的 info
     */
    @ExceptionHandler(value = ApiException.class)
    public ApiResponses<Object> handle(ApiException e) {

        if (e.getErrorCode() != null) {
            return ApiResponses.failed(e.getErrorCode(), e.getMessage());
        } else {
            return ApiResponses.failed(e.getMessage());
        }
    }

    /**
     * 参数验证异常
     *
     * @param e 异常
     * @return 返回结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponses<Object> handleMethodArgumentException(MethodArgumentNotValidException e) {
        log.error("参数异常 = {}", e.toString());
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(item -> errors.add(item.getDefaultMessage()));
        return ApiResponses.failed(ResultCode.API_PARAMS_ERROR, errors, "参数不正确！");
    }

}