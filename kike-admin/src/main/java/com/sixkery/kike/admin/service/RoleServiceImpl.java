package com.sixkery.kike.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sixkery.kike.admin.dto.RoleDto;
import com.sixkery.kike.admin.entity.system.RoleDo;
import com.sixkery.kike.admin.mapper.RoleMapper;
import com.sixkery.kike.common.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public List<RoleDto> listAll() {
        QueryWrapper<RoleDo> queryWrapper = new QueryWrapper<>();
        List<RoleDo> roleDos = roleMapper.selectList(queryWrapper);
        List<RoleDto> roleDtoList = new ArrayList<>();
        roleDos.forEach(roleDo -> {
            RoleDto roleDto = new RoleDto();
            BeanUtils.copyProperties(roleDo,roleDto);
            roleDtoList.add(roleDto);
        });

        return roleDtoList;
    }
}
