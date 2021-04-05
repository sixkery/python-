package com.sixkery.kike.api.service;

import com.sixkery.kike.api.mapper.UserMapper;
import com.sixkery.kike.api.vo.UserVo;
import com.sixkery.kike.common.utils.NameUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: sixkery
 * @date:2021/4/5
 */
@Service
public class UserServiceImpl implements UserDetailsService {


    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserVo userVo;
        if (NameUtil.username(username)) {
            userVo = userMapper.findUsername(username);
        } else if (NameUtil.email(username)) {
            userVo = userMapper.findByEmail(username);
        } else {
            userVo = userMapper.findByMobile(username);
        }
        if (userVo == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }
//        userVo.setPassword(new BCryptPasswordEncoder().encode(userVo.getPassword()));
        return userVo;

    }
}
