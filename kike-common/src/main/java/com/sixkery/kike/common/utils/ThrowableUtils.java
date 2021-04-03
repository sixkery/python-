package com.sixkery.kike.common.utils;

import cn.hutool.core.collection.CollUtil;
import com.sixkery.kike.common.Constant;
import com.sixkery.kike.common.exception.ApiException;
import com.sixkery.kike.common.response.ResultCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 抛出异常工具类
 *
 * @author sixkery
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ThrowableUtils {
    /**
     * 校验参数正确,拼装字段名和值到错误信息
     *
     * @param result 返回错误信息
     */
    public static void checkParamArgument(BindingResult result) {
        if (result != null && result.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            HashMap<String, String> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            if (CollUtil.isNotEmpty(errors)) {
                FieldError error = errors.get(0);
                String rejectedValue = Objects.toString(error.getRejectedValue(), "");
                String defMsg = error.getDefaultMessage();
                // 排除类上面的注解提示
                if (rejectedValue.contains(Constant.DELIMITER_TO)) {
                    // 自己去确定错误字段
                    sb.append(defMsg);
                } else {
                    if (Constant.DELIMITER_COLON.contains(defMsg)) {
                        map.put(error.getField(), defMsg);
                        sb.append(error.getField()).append(" ").append(defMsg);
                    } else {
                        map.put(error.getField(), defMsg);
                        sb.append(error.getField()).append(" ").append(defMsg);
                    }
                }
            } else {
                String msg = result.getAllErrors().get(0).getDefaultMessage();
                sb.append(msg);
            }
            log.info("出错的字段 = {}", map);
            throw new ApiException(ResultCode.API_PARAMS_ERROR, map);
        }

    }
}
