package com.sixkery.kike.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author sixkery
 * @date 2020/10/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "角色名")
    private String name;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "是否可用,0:不可用，1：可用")
    private Integer status;

    @ApiModelProperty(value = "添加时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "用户数")
    private Integer roleCount;


}
