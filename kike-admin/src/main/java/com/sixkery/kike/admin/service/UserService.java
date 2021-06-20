package com.sixkery.kike.admin.service;

import com.sixkery.kike.admin.dto.UserDto;
import com.sixkery.kike.admin.entity.system.RoleDo;
import com.sixkery.kike.common.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author sixkery
 */
public interface UserService {
    /**
     * 登录之后获取用户信息
     *
     * @return 用户菜单 角色
     */
    Map<String, Object> userInfo();


    /**
     * 查询全部用户信息
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @param keyword  搜索关键词
     * @return 用户信息
     */
    PageInfo<UserDto> findAll(Integer pageNum, Integer pageSize, String keyword);


    /**
     * 新增用户的信息
     *
     * @param userDto 用户信息
     * @return 是否新增成功
     */
    Integer register(UserDto userDto);

    /**
     * 更新用户的信息
     *
     * @param id      用户 id
     * @param userDto 用户信息
     * @return 是否更新成功
     */
    Integer updateUser(Long id, UserDto userDto);

    /**
     * 更新用户的启用状态
     *
     * @param id     用户的 ID
     * @param status 用户 状态
     * @return 是否更新成功
     */
    Integer updateStatus(Long id,Integer status);

    /**
     * 获取指定用户的角色
     *
     * @param id 用户 id
     * @return 角色信息
     */
    List<RoleDo> getRoleList(Long id);

    /**
     * 更新用户的角色
     *
     * @param adminId 用户 id
     * @param roleIds 角色列表
     * @return 是否更新成功
     */
    Integer updateRole(Long adminId, List<Long> roleIds);

}
