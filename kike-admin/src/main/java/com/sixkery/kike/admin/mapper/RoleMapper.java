package com.sixkery.kike.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sixkery.kike.admin.dto.RoleDto;
import com.sixkery.kike.admin.entity.system.RoleDo;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author sixkery
 * @since 2020-11-27
 */
public interface RoleMapper extends BaseMapper<RoleDo> {

    /**
     * 根据用户 ID 获取用户拥有的菜单权限
     *
     * @param userId 用户 ID
     * @return 菜单权限
     */
    List<RoleDo> findByUserId(Long userId);

    List<RoleDto> findAll();

}
