package com.common.excel;

import lombok.Data;

import java.util.List;

/**
 * 对应Excel中的Sheet
 *
 * @author lazy
 */
@Data
public class ExcelSheet<T> {

    private String[] headers;

    private String name;

    private List<T> data;

    private String[] exportFields;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
