package com.sixkery.kike.api.service;

import com.sixkery.kike.api.mapper.MenuMapper;
import com.sixkery.kike.api.mapper.UserMapper;
import com.sixkery.kike.api.vo.UserVo;
import com.sixkery.kike.common.utils.NameUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springfox.documentation.spi.service.contexts.SecurityContext;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: sixkery
 * @date:2021/4/5
 */
@Service
public class UserServiceImpl implements UserDetailsService,UserService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

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
        return userVo;
    }

    @Override
    public Map<String, Object> userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        Object credentials = authentication.getCredentials();
        menuMapper.findByUserId(1);
        return null;
    }
}
