package com.sixkery.kike.admin.service;

import com.sixkery.kike.admin.dto.MenuDto;
import com.sixkery.kike.admin.dto.RoleDto;
import com.sixkery.kike.common.PageInfo;

import java.util.List;

/**
 * @author sixkery
 */
public interface RoleService {

    /**
     * 查询全部角色信息
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @param keyword  搜索关键词
     * @return 角色信息
     */
    PageInfo<RoleDto> list(Integer pageNum, Integer pageSize, String keyword);

    /**
     * 查询全部角色信息
     *
     * @return 角色信息
     */
    List<RoleDto> listAll();

    /**
     * 更新角色的启用状态
     *
     * @param id     角色的 ID
     * @param status 角色 状态
     * @return 是否更新成功
     */
    Integer updateStatus(Long id, Integer status);

    /**
     * 查询角色拥有的菜单
     *
     * @param id 角色 ID
     * @return 角色信息
     */
    List<MenuDto> listMenu(Long id);

    /**
     * 新增角色
     *
     * @param roleDto 角色信息
     * @return 新增个数
     */
    Integer create(RoleDto roleDto);


    /**
     * 删除角色
     *
     * @param ids 角色ids
     * @return 删除个数
     */
    Integer delete(List<Long> ids);

    /**
     * 编辑角色
     *
     * @param roleDto 角色信息
     * @param id      角色 ID
     * @return 角色信息
     */
    Integer update(RoleDto roleDto, Long id);

    /**
     * 角色分配菜单
     *
     * @param roleId  角色 id
     * @param menuIds 菜单 IDs
     * @return 是否分配成功
     */
    Integer allocMenu(Long roleId, List<Long> menuIds);

}
