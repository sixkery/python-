package com.sixkery.kike.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sixkery.kike.admin.entity.system.UserDO;
import com.sixkery.kike.admin.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 继承 security 默认的获取用户名的类
 *
 * @author: sixkery
 * @date:2021/4/1
 */
@Service
public class CustomerServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserDO> objectQueryWrapper = new QueryWrapper<>();
        objectQueryWrapper.eq("username", username);
        UserDO userDo = userMapper.selectOne(objectQueryWrapper);

        List<GrantedAuthority> admin = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
        return new User(username, userDo.getPassword(), admin);

    }
}
