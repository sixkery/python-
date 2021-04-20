package com.sixkery.kike.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sixkery.kike.admin.entity.system.MenuDO;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author sixkery
 * @since 2020-11-27
 */
public interface MenuMapper extends BaseMapper<MenuDO> {


    /**
     * 根据用户 ID 获取用户拥有的菜单权限
     *
     * @param userId 用户 ID
     * @return 菜单权限
     */
    List<MenuDO> findByUserId(Long userId);

}
