package com.sixkery.kike.common;

import lombok.Data;

import java.util.Collection;

/**
 * 分页对象
 *
 * @author sixkery
 * @date 2020/6/27
 */
@Data
public class PageInfo<T> {
    /**
     * 当前第几页
     */
    private long currentPage;

    /**
     * 每页条数,默认八条
     */
    private long pageSize;

    /**
     * 总记录数
     */
    private long total;

    /**
     * 查询的内容
     */
    private Collection<T> content;

}
