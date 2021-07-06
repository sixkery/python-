package com.sixkery.kike.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sixkery.kike.admin.dto.ResourceDto;
import com.sixkery.kike.admin.dto.RoleDto;
import com.sixkery.kike.admin.entity.system.MenuDo;
import com.sixkery.kike.admin.entity.system.ResourceDo;
import com.sixkery.kike.admin.entity.system.RoleDo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询角色的用户数
     *
     * @return 用户数
     */
    List<RoleDto> findAll();

    /**
     * 查询当前角色的菜单
     *
     * @param id 角色 ID
     * @return 菜单信息
     */
    List<MenuDo> findMenu(@Param("id") Long id);


    /**
     * 查询当前角色的资源
     *
     * @param id 角色 ID
     * @return 资源信息
     */
    List<ResourceDo> findResource(@Param("id") Long id);
    /**
     * 删除角色和菜单关系
     *
     * @param roleId 角色
     */
    void delRelation(Long roleId);

}
