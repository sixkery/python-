package com.sixkery.kike.admin.service;

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

}
