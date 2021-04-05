package com.sixkery.kike.api.config.vo;

import com.sixkery.kike.api.config.dto.PermissionDto;
import com.sixkery.kike.api.config.dto.RoleDto;
import com.sixkery.kike.api.entity.system.UserDO;
import lombok.Data;

import java.util.List;

/**
 * @author sixkery
 */
@Data
public class UserVo extends UserDO {
    /**
     * 权限
     */
    private List<PermissionDto> permissionDos;

    /**
     * 角色
     */
    private List<RoleDto> roleDos;


}
