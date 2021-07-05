package com.sixkery.kike.admin.service;

import com.sixkery.kike.admin.dto.MenuDto;
import com.sixkery.kike.common.PageInfo;

import java.util.List;

/**
 * @author sixkery
 */
public interface MenuService {


    /**
     * 查询菜单 tree
     *
     * @return 角色信息
     */
    List<MenuDto> treeList();

    /**
     * 分页查询菜单
     *
     * @param parentId 父级 ID
     * @param pageSize 每页大小
     * @param pageNum  第几页
     * @return 菜单信息
     */
    PageInfo<MenuDto> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 根据 ID 查询菜单
     *
     * @param id id
     * @return 菜单信息
     */
    MenuDto findOne(Long id);

    /**
     * 根据 ID 更新菜单
     *
     * @param id      id
     * @param menuDto 入参数据
     * @return 更新条数
     */
    Integer update(Long id, MenuDto menuDto);

    /**
     * 创建菜单
     *
     * @param menuDto 入参数据
     * @return 新增条数
     */
    Integer create(MenuDto menuDto);

    Integer delete(Long id);
}
