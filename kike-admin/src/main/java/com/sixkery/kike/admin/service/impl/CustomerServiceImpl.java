package com.sixkery.kike.admin.service.impl;

import com.sixkery.kike.admin.config.SecurityUserDetails;
import com.sixkery.kike.admin.config.vo.UserVo;
import com.sixkery.kike.admin.service.UserService;
import com.sixkery.kike.common.utils.NameUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 继承 security 默认的获取用户名的类
 *
 * @author: sixkery
 * @date:2021/4/1
 */
@Service
public class CustomerServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserVo userVo;
        if (NameUtil.username(username)) {
            userVo = userService.findByUsername(username);
        } else if (NameUtil.email(username)) {
            userVo = userService.findByEmail(username);
        } else {
            userVo = userService.findByMobile(username);
        }
        if (userVo == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        return new SecurityUserDetails(userVo);
    }
}
