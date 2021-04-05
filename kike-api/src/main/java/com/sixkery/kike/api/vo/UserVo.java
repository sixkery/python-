package com.sixkery.kike.api.vo;

import com.sixkery.kike.api.dto.PermissionDto;
import com.sixkery.kike.api.dto.RoleDto;
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
