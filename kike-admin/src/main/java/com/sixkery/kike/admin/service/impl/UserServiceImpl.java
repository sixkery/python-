package com.sixkery.kike.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.sixkery.kike.admin.entity.system.UserDO;
import com.sixkery.kike.admin.mapper.UserMapper;
import com.sixkery.kike.admin.service.UserService;
import com.sixkery.kike.admin.web.dto.form.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public UserDTO login(UserDTO userDTO) {
        QueryWrapper<UserDO> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("username", userDTO.getUsername()).eq("password",userDTO.getPassword());
        UserDO user = userMapper.selectOne(objectQueryWrapper);
        UserDTO userDTO1 = new UserDTO();
        BeanUtils.copyProperties(user,userDTO1);
        return userDTO1;
    }

    @Override
    public PageInfo<UserDO> findAll() {
        PageMethod.startPage(0, 8);
        QueryWrapper<UserDO> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("nickName", "zhangyu");
        List<UserDO> rstList = userMapper.selectList(objectQueryWrapper);

        return new PageInfo<>(rstList);


    }
}
