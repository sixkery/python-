package com.sixkery.kike.admin.service;

import com.sixkery.kike.admin.dto.MenuDto;

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
}
