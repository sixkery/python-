package com.sixkery.kike.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sixkery.kike.admin.dto.MenuDto;
import com.sixkery.kike.admin.entity.system.MenuDo;
import com.sixkery.kike.admin.mapper.MenuMapper;
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
        List<MenuDo> childrenMenus = menuDos.stream().filter(item -> item.getParentId() != 0).collect(Collectors.toList());



        return null;
    }
}
