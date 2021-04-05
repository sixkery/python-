package com.sixkery.kike.api.config.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sixkery
 */
@Data
public class PermissionDto {
    @ApiModelProperty(value = "菜单标题")
    private String title;

    @ApiModelProperty(value = "页面路径/资源链接url")
    private String path;
}
