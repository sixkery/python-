package com.common;

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
     * 页码
     */
    private int page;

    /**
     * 每页条数,默认八条
     */
    private int size;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总记录数
     */
    private int totalRecords;

    /**
     * 查询的内容
     */
    private Collection<T> content;

}
