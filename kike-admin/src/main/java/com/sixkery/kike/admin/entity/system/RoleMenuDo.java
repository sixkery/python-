package com.sixkery.kike.admin.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sixkery.kike.common.BaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 角色菜单关系
 * </p>
 *
 * @author sixkery
 * @since 2021-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_role_menu")
@ApiModel(value = "角色菜单关系", description = "角色菜单关系表")
public class RoleMenuDo extends BaseDO implements Serializable {

    @ApiModelProperty(value = "关系ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色Id")
    private Long roleId;

    @ApiModelProperty(value = "菜单Id")
    private Long menuId;

}
