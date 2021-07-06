package com.sixkery.kike.admin.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sixkery.kike.admin.dto.MenuDto;
import com.sixkery.kike.admin.dto.ResourceDto;
import com.sixkery.kike.admin.dto.RoleDto;
import com.sixkery.kike.admin.entity.system.MenuDo;
import com.sixkery.kike.admin.entity.system.ResourceDo;
import com.sixkery.kike.admin.entity.system.RoleDo;
import com.sixkery.kike.admin.entity.system.RoleMenuDo;
import com.sixkery.kike.admin.mapper.RoleMapper;
import com.sixkery.kike.admin.mapper.RoleMenuMapper;
import com.sixkery.kike.common.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: sixkery
 * @date:2021/6/19
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;


    /**
     * 查询全部角色信息
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @param keyword  搜索关键词
     * @return 角色信息
     */
    @Override
    public PageInfo<RoleDto> list(Integer pageNum, Integer pageSize, String keyword) {
        QueryWrapper<RoleDo> qw = new QueryWrapper<>();
        if (StrUtil.isNotBlank(keyword)) {
            qw.like("name", keyword).or().like("description", keyword);
        }

        Page<RoleDo> objectPage = new Page<>(pageNum, pageSize);

        IPage<RoleDo> roleDoPage = roleMapper.selectPage(objectPage, qw);
        // 获取用户数
        List<RoleDto> roleDtoList = roleMapper.findAll();

        PageInfo<RoleDto> pageInfo = new PageInfo<>();
        pageInfo.setPageSize(roleDoPage.getSize());
        pageInfo.setCurrentPage(roleDoPage.getCurrent());
        pageInfo.setTotal(roleDoPage.getTotal());
        List<RoleDo> userDoList = roleDoPage.getRecords();
        List<RoleDto> roleList = new ArrayList<>();
        userDoList.forEach(roleDo -> {

            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(roleDo, roleDto);
            Optional<RoleDto> first = roleDtoList.stream().filter(item -> roleDo.getId().equals(item.getId())).findFirst();
            if (first.isPresent()) {
                RoleDto role = first.get();
                roleDto.setRoleCount(role.getRoleCount());
            }
            roleList.add(roleDto);
        });
        pageInfo.setList(roleList);
        return pageInfo;
    }

    /**
     * 查询全部角色信息
     *
     * @return 角色信息
     */
    @Override
    public List<RoleDto> listAll() {

        QueryWrapper<RoleDo> queryWrapper = new QueryWrapper<>();
        List<RoleDo> roleDos = roleMapper.selectList(queryWrapper);
        List<RoleDto> roleDtoList = new ArrayList<>();
        roleDos.forEach(roleDo -> {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(roleDo, roleDto);
            roleDtoList.add(roleDto);
        });
        return roleDtoList;
    }

    @Override
    public Integer updateStatus(Long id, Integer status) {
        RoleDo roleDo = new RoleDo();
        roleDo.setId(id);
        roleDo.setStatus(status);
        roleDo.setUpdateTime(LocalDateTime.now());
        return roleMapper.updateById(roleDo);

    }

    /**
     * 查询角色拥有的菜单
     *
     * @param id 角色 ID
     * @return 角色信息
     */
    @Override
    public List<MenuDto> listMenu(Long id) {
        List<MenuDo> menus = roleMapper.findMenu(id);

        List<MenuDto> menuDtoList = new ArrayList<>();
        menus.forEach(menuDo -> {
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(menuDo, menuDto);
            menuDtoList.add(menuDto);
        });
        return menuDtoList;
    }

    @Override
    public List<ResourceDto> listResource(Long id) {
        List<ResourceDo> resourceDos = roleMapper.findResource(id);

        List<ResourceDto> resourceDtoList = new ArrayList<>();
        resourceDos.forEach(item -> {
            ResourceDto resourceDto = new ResourceDto();
            BeanUtils.copyProperties(item, resourceDto);
            resourceDtoList.add(resourceDto);
        });
        return resourceDtoList;
    }

    @Override
    public Integer create(RoleDto roleDto) {
        RoleDo roleDo = new RoleDo();
        BeanUtils.copyProperties(roleDto, roleDo);
        roleDo.setCreateTime(LocalDateTime.now());
        return roleMapper.insert(roleDo);
    }

    @Override
    public Integer delete(List<Long> ids) {
        return roleMapper.deleteBatchIds(ids);
    }

    @Override
    public Integer update(RoleDto roleDto, Long id) {
        RoleDo roleDo = new RoleDo();
        BeanUtils.copyProperties(roleDto, roleDo);
        roleDo.setId(id);
        roleDo.setCreateTime(null);
        roleDo.setUpdateTime(LocalDateTime.now());
        return roleMapper.updateById(roleDo);

    }

    /**
     * 角色分配菜单
     *
     * @param roleId  角色 id
     * @param menuIds 菜单 IDs
     * @return 是否分配成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer allocMenu(Long roleId, List<Long> menuIds) {
        // 1. 删除关系
        roleMapper.delRelation(roleId);
        // 2. 建立新的关系
        for (Long menuId : menuIds) {
            RoleMenuDo roleMenuDo = new RoleMenuDo();
            roleMenuDo.setRoleId(roleId);
            roleMenuDo.setMenuId(menuId);
            roleMenuMapper.insert(roleMenuDo);
        }
        return menuIds.size();
    }
}
