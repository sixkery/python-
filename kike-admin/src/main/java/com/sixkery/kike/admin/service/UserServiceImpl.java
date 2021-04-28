package com.sixkery.kike.admin.service;

import com.sixkery.kike.admin.dto.UserDto;
import com.sixkery.kike.admin.entity.system.MenuDO;
import com.sixkery.kike.admin.entity.system.RoleDO;
import com.sixkery.kike.admin.mapper.MenuMapper;
import com.sixkery.kike.admin.mapper.RoleMapper;
import com.sixkery.kike.admin.mapper.UserMapper;
import com.sixkery.kike.admin.vo.UserVo;
import com.sixkery.kike.common.utils.NameUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: sixkery
 * @date:2021/4/5
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMapper roleMapper;

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

        // 认证信息中提取用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        UserVo userVo = userMapper.findUsername(username);
        Long userId = userVo.getId();
        // 获取菜单
        List<MenuDO> menuDos = menuMapper.findByUserId(userId);
        HashMap<String, Object> resultMap = new HashMap<>(3);
        // 获取角色信息
        List<RoleDO> roleDos = roleMapper.findByUserId(userId);
        // 组转装数据
        resultMap.put("username", username);
        resultMap.put("menuList", menuDos);
        resultMap.put("roleList", roleDos);

        return resultMap;
    }

    @Override
    public List<UserDto> findAll() {
        userMapper.
        return null;
    }
}
