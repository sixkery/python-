package com.sixkery.kike.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sixkery.kike.admin.entity.system.UserDo;
import com.sixkery.kike.admin.vo.UserVo;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author sixkery
 * @since 2020-11-27
 */

public interface UserMapper extends BaseMapper<UserDo> {
    /**
     * 根据用户名查找用户实体
     *
     * @param username 用户名
     * @return 用户实体
     */
    UserDo findUsername(String username);

    /**
     * 根据用户名查找用户实体
     *
     * @param mobile 用户名
     * @return 用户实体
     */
    UserDo findByMobile(String mobile);

    /**
     * 根据用户名查找用户实体
     *
     * @param email 用户名
     * @return 用户实体
     */
    UserDo findByEmail(String email);
}
