package com.sixkery.kike.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sixkery.kike.admin.dto.ResourceCategoryDto;
import com.sixkery.kike.admin.dto.ResourceDto;
import com.sixkery.kike.admin.entity.system.ResourceCategoryDo;
import com.sixkery.kike.admin.entity.system.ResourceDo;
import com.sixkery.kike.admin.mapper.ResourceCategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sixkery
 * @date 2021/7/6
 */
@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Resource
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategoryDto> findAll() {
        QueryWrapper<ResourceCategoryDo> qw = new QueryWrapper<>();
        List<ResourceCategoryDo> resourceCategoryDos = resourceCategoryMapper.selectList(qw);
        List<ResourceCategoryDto> resourceCategoryDtoList = new ArrayList<>();
        resourceCategoryDos.forEach(item->{
            ResourceCategoryDto resourceCategoryDto = new ResourceCategoryDto();
            BeanUtils.copyProperties(item, resourceCategoryDto);
            resourceCategoryDtoList.add(resourceCategoryDto);
        });


        return resourceCategoryDtoList;
    }
}
