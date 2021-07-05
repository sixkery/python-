package com.sixkery.kike.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sixkery.kike.admin.dto.MenuDto;
import com.sixkery.kike.admin.entity.system.MenuDo;
import com.sixkery.kike.admin.mapper.MenuMapper;
import com.sixkery.kike.common.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Override
    public PageInfo<MenuDto> list(Long parentId, Integer pageSize, Integer pageNum) {
        QueryWrapper<MenuDo> qw = new QueryWrapper<>();
        Page<MenuDo> objectPage = new Page<>(pageNum, pageSize);

        IPage<MenuDo> menuDoIPage = menuMapper.selectPage(objectPage, qw);

        PageInfo<MenuDto> pageInfo = new PageInfo<>();
        pageInfo.setPageSize(menuDoIPage.getSize());
        pageInfo.setCurrentPage(menuDoIPage.getCurrent());
        pageInfo.setTotal(menuDoIPage.getTotal());
        List<MenuDo> menuDos = menuDoIPage.getRecords();
        List<MenuDto> menuDtoList = new ArrayList<>();
        menuDos.forEach(menuDo -> {

            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(menuDo, menuDto);

            menuDtoList.add(menuDto);
        });
        pageInfo.setList(menuDtoList);
        return pageInfo;
    }

    @Override
    public MenuDto findOne(Long id) {
        MenuDo menuDo = menuMapper.selectById(id);
        MenuDto menuDto = new MenuDto();
        BeanUtils.copyProperties(menuDo, menuDto);
        return menuDto;
    }

    @Override
    public Integer update(Long id, MenuDto menuDto) {
        MenuDo menuDo = new MenuDo();
        BeanUtils.copyProperties(menuDto, menuDo);
        menuDo.setCreateTime(null);
        menuDo.setUpdateTime(LocalDateTime.now());
        menuDo.setId(id);
        return menuMapper.updateById(menuDo);
    }

    @Override
    public Integer create(MenuDto menuDto) {
        MenuDo menuDo = new MenuDo();
        BeanUtils.copyProperties(menuDto, menuDo);
        menuDo.setCreateTime(LocalDateTime.now());
        return menuMapper.insert(menuDo);
    }

    @Override
    public Integer delete(Long id) {
        return menuMapper.deleteById(id);
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
