package com.sixkery.kike.common;

import lombok.Data;

/**
 * 控制器接收的分页参数
 *
 * @author sixkery
 * @date 2020/6/27
 */
@Data
public class Pageable {
    /**
     * 页码
     */
    private int page;

    /**
     * 每页条数,默认八条
     */
    private int size = 8;

    /**
     * 排序字段 example: id desc
     */
    private String sort;


}



