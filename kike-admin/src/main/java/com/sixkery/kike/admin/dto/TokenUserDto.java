package com.sixkery.kike.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sixkery
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenUserDto {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "权限")
    private List<String> permissions;


    @ApiModelProperty(value = "是否保存登录")
    private Boolean saveLogin;
}
