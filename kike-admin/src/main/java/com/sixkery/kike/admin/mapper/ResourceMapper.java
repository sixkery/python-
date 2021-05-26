package com.sixkery.kike.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sixkery.kike.admin.entity.system.ResourceDo;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author sixkery
 * @since 2020-11-27
 */

public interface ResourceMapper extends BaseMapper<ResourceDo> {
    /**
     * 根据用户名查找用户的资源
     *
     * @param userId 用户ID
     * @return 用户资源
     */
    List<ResourceDo> findByUserId(Long userId);

}
