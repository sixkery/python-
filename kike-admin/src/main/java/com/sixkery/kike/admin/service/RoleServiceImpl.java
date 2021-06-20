package com.sixkery.kike.admin.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sixkery.kike.admin.dto.RoleDto;
import com.sixkery.kike.admin.entity.system.RoleDo;
import com.sixkery.kike.admin.mapper.RoleMapper;
import com.sixkery.kike.common.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: sixkery
 * @date:2021/6/19
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Resource
    private RoleMapper roleMapper;


    @Override
    public PageInfo<RoleDto> list(Integer pageNum, Integer pageSize, String keyword) {
        QueryWrapper<RoleDo> qw = new QueryWrapper<>();
        if (StrUtil.isNotBlank(keyword)) {
            qw.like("name", keyword).or().like("description", keyword);
        }

        Page<RoleDo> objectPage = new Page<>(pageNum, pageSize);

        IPage<RoleDo> roleDoPage = roleMapper.selectPage(objectPage, qw);

        PageInfo<RoleDto> pageInfo = new PageInfo<>();
        pageInfo.setPageSize(roleDoPage.getSize());
        pageInfo.setCurrentPage(roleDoPage.getCurrent());
        pageInfo.setTotal(roleDoPage.getTotal());
        List<RoleDo> userDoList = roleDoPage.getRecords();
        List<RoleDto> roleList = new ArrayList<>();
        userDoList.forEach(roleDo -> {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(roleDo, roleDto);
            roleList.add(roleDto);
        });
        pageInfo.setList(roleList);
        return pageInfo;
    }

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
}
