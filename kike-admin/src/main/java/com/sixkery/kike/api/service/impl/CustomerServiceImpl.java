package com.sixkery.kike.api.service.impl;

import com.sixkery.kike.api.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 继承 security 默认的获取用户名的类
 *
 * @author: sixkery
 * @date:2021/4/1
 */
@Service("userDetailsService")
public class CustomerServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
//        UserVo userVo;
//        if (NameUtil.username(username)) {
//            userVo = userService.findByUsername(username);
//        } else if (NameUtil.email(username)) {
//            userVo = userService.findByEmail(username);
//        } else {
//            userVo = userService.findByMobile(username);
//        }
//        if (userVo == null) {
//            throw new UsernameNotFoundException("用户名不存在！");
//        }
        List<GrantedAuthority> role = AuthorityUtils.commaSeparatedStringToAuthorityList("role");
        return new User("sixkery", new BCryptPasswordEncoder().encode("123"), role);

//        return new SecurityUserDetails(userVo);
    }
}
