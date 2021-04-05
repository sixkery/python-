package com.sixkery.kike.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sixkery.kike.admin.config.vo.UserVo;
import com.sixkery.kike.admin.entity.system.UserDO;
import com.sixkery.kike.admin.mapper.UserMapper;
import com.sixkery.kike.admin.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author sixkery
 * @since 2020-11-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 根据用户名获得用户信息
     *
     * @param username 用户名
     * @return 用户信息，密码，权限 等
     */
    @Override
    public UserVo findByUsername(String username) {
        return userMapper.findUsername(username);
    }

    @Override
    public UserVo findByMobile(String mobile) {
        return userMapper.findByMobile(mobile);
    }

    @Override
    public UserVo findByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public Object findAll() {
        return null;
    }


}

