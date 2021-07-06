package com.sixkery.kike.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sixkery.kike.admin.dto.ResourceDto;
import com.sixkery.kike.admin.entity.system.ResourceDo;
import com.sixkery.kike.admin.mapper.ResourceMapper;
import com.sixkery.kike.common.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: sixkery
 * @date:2021/5/8
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceMapper resourceMapper;


    @Override
    public List<ResourceDo> findAll() {
        QueryWrapper<ResourceDo> queryWrapper = new QueryWrapper<>();
        return resourceMapper.selectList(queryWrapper);

    }

    /**
     * 分页查询资源
     *
     * @param pageSize 每页大小
     * @param pageNum  第几页
     * @return 资源信息
     */
    @Override
    public PageInfo<ResourceDto> list(Integer pageSize, Integer pageNum) {
        QueryWrapper<ResourceDo> qw = new QueryWrapper<>();

        Page<ResourceDo> objectPage = new Page<>(pageNum, pageSize);
        IPage<ResourceDo> resourcePage = resourceMapper.selectPage(objectPage, qw);

        PageInfo<ResourceDto> pageInfo = new PageInfo<>();
        pageInfo.setPageSize(resourcePage.getSize());
        pageInfo.setCurrentPage(resourcePage.getCurrent());
        pageInfo.setTotal(resourcePage.getTotal());
        List<ResourceDo> resourceDos = resourcePage.getRecords();
        List<ResourceDto> resourceDtoList = new ArrayList<>();
        resourceDos.forEach(item -> {
            ResourceDto resourceDto = new ResourceDto();
            BeanUtils.copyProperties(item, resourceDto);

            resourceDtoList.add(resourceDto);
        });
        pageInfo.setList(resourceDtoList);
        return pageInfo;
    }
}
