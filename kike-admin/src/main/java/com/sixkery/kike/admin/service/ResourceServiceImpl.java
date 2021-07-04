package com.sixkery.kike.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sixkery.kike.admin.entity.system.ResourceDo;
import com.sixkery.kike.admin.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
