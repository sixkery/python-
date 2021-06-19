package com.sixkery.kike.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ApiModelProperty(value = "角色名 以ROLE_开头")
    private String name;

    @ApiModelProperty(value = "备注")
    private String description;


}
