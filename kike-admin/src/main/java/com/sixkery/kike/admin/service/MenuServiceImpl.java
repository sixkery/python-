package com.sixkery.kike.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sixkery.kike.admin.dto.MenuDto;
import com.sixkery.kike.admin.entity.system.MenuDo;
import com.sixkery.kike.admin.mapper.MenuMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sixkery
 * @date 2021/6/27
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<MenuDto> treeList() {
        QueryWrapper<MenuDo> qw = new QueryWrapper<>();
        List<MenuDo> menuDos = menuMapper.selectList(qw);
        return menuDos.stream()
                .filter(item -> item.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuDos))
                .collect(Collectors.toList());

    }

    /**
     * 将 MenuDo 转化为 MenuDto 并设置 children 属性
     */
    private MenuDto covertMenuNode(MenuDo menu, List<MenuDo> menuList) {
        MenuDto node = new MenuDto();
        BeanUtils.copyProperties(menu, node);
        List<MenuDto> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
