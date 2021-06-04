package com.sixkery.kike.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author sixkery
 * @since 2020-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto {

    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别 0男 1女 2保密")
    private String gender;

    @ApiModelProperty(value = "状态 0锁定 1有效")
    private Integer status;

}