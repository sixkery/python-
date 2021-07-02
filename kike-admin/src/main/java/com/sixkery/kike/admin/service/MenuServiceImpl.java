package com.sixkery.kike.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sixkery.kike.admin.dto.MenuDto;
import com.sixkery.kike.admin.entity.system.MenuDo;
import com.sixkery.kike.admin.mapper.MenuMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author sixkery
 * @date 2021/6/27
 */
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<MenuDto> treeList() {
        QueryWrapper<MenuDo> qw = new QueryWrapper<>();
        List<MenuDo> menuDos = menuMapper.selectList(qw);

        return null;
    }
}
