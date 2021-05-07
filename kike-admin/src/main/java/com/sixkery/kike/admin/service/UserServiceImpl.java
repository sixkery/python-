package com.sixkery.kike.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sixkery.kike.admin.dto.UserDto;
import com.sixkery.kike.admin.entity.system.MenuDo;
import com.sixkery.kike.admin.entity.system.RoleDo;
import com.sixkery.kike.admin.entity.system.UserDo;
import com.sixkery.kike.admin.mapper.MenuMapper;
import com.sixkery.kike.admin.mapper.RoleMapper;
import com.sixkery.kike.admin.mapper.UserMapper;
import com.sixkery.kike.admin.vo.UserVo;
import com.sixkery.kike.common.PageInfo;
import com.sixkery.kike.common.utils.NameUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        List<MenuDo> menuDos = menuMapper.findByUserId(userId);
        HashMap<String, Object> resultMap = new HashMap<>(3);
        // 获取角色信息
        List<RoleDo> roleDos = roleMapper.findByUserId(userId);
        // 组转装数据
        resultMap.put("username", username);
        resultMap.put("menuList", menuDos);
        resultMap.put("roleList", roleDos);

        return resultMap;
    }

    @Override
    public PageInfo<UserDto> findAll() {
        QueryWrapper<UserDo> queryWrapper = new QueryWrapper<>();

        Page<UserDo> objectPage = new Page<>(1, 2);
        IPage<UserDo> userDoPage = userMapper.selectPage(objectPage, queryWrapper);

        PageInfo<UserDto> pageInfo = new PageInfo<>();
        pageInfo.setPageSize(userDoPage.getSize());
        pageInfo.setCurrentPage(userDoPage.getCurrent());
        pageInfo.setTotal(userDoPage.getTotal());
        List<UserDo> userDoList = userDoPage.getRecords();
        List<UserDto> userList = new ArrayList<>();
        userDoList.forEach(userDo -> {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(userDo, userDto);
            userList.add(userDto);
        });
        pageInfo.setContent(userList);
        return pageInfo;
    }
}
