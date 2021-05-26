package com.sixkery.kike.common;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 基础
 *
 * @author sixkery
 */
@Data
public class BaseDO {

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
