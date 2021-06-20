package com.sixkery.kike.admin.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sixkery.kike.admin.configuration.security.AdminUserDetails;
import com.sixkery.kike.admin.dto.UserDto;
import com.sixkery.kike.admin.entity.system.*;
import com.sixkery.kike.admin.mapper.*;
import com.sixkery.kike.common.PageInfo;
import com.sixkery.kike.common.utils.NameUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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


    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Resource
    private ResourceMapper resourceMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDo userDo;
        if (NameUtil.username(username)) {
            userDo = userMapper.findUsername(username);
        } else if (NameUtil.email(username)) {
            userDo = userMapper.findByEmail(username);
        } else {
            userDo = userMapper.findByMobile(username);
        }
        if (userDo == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        List<ResourceDo> resourceDoList = resourceMapper.findByUserId(userDo.getId());

        return new AdminUserDetails(userDo, resourceDoList);
    }

    @Override
    public Map<String, Object> userInfo() {

        // 认证信息中提取用户名

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AdminUserDetails principal = (AdminUserDetails) authentication.getPrincipal();

        UserDo userDo = userMapper.findUsername(principal.getUsername());
        Long userId = userDo.getId();
        // 获取菜单
        List<MenuDo> menuDos = menuMapper.findByUserId(userId);
        HashMap<String, Object> resultMap = new HashMap<>(3);
        // 获取角色信息
        List<RoleDo> roleDos = roleMapper.findByUserId(userId);
        // 组转装数据
        resultMap.put("username", userDo.getUsername());
        resultMap.put("menus", menuDos);
        resultMap.put("icon", userDo.getIcon());
        resultMap.put("roles", roleDos);

        return resultMap;
    }

    @Override
    public PageInfo<UserDto> findAll(Integer pageNum, Integer pageSize, String keyword) {
        QueryWrapper<UserDo> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(keyword)) {
            queryWrapper.like("username", keyword).or().like("nickname", keyword);
        }
        Page<UserDo> objectPage = new Page<>(pageNum, pageSize);

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
        pageInfo.setList(userList);
        return pageInfo;
    }

    @Override
    public Integer register(UserDto userDto) {
        userDto.setCreateTime(LocalDateTime.now());
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userDto, userDo);
        userDo.setPassword(passwordEncoder.encode(userDo.getPassword()));
        return userMapper.insert(userDo);
    }

    @Override
    public Integer updateUser(Long id, UserDto userDto) {

        UserDo userDo = userMapper.selectById(id);
        if (userDo.getPassword().equals(userDto.getPassword())) {
            userDto.setPassword(null);
        } else {
            String encodePassword = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encodePassword);
        }
        userDto.setCreateTime(null);
        userDto.setUpdateTime(LocalDateTime.now());
        UserDo user = new UserDo();
        BeanUtils.copyProperties(userDto, user);
        return userMapper.updateById(user);

    }

    @Override
    public Integer updateStatus(Long id, Integer status) {
        UserDo userDo = new UserDo();
        userDo.setId(id);
        userDo.setStatus(status);
        userDo.setUpdateTime(LocalDateTime.now());
        return userMapper.updateById(userDo);
    }

    @Override
    public List<RoleDo> getRoleList(Long id) {
        return roleMapper.findByUserId(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateRole(Long adminId, List<Long> roleIds) {
        // 1. 删除已有的关系
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", adminId);
        userRoleMapper.deleteByMap(map);
        // 2. 绑定现有的关系
        List<UserRoleDo> userRoleDos = new ArrayList<>();
        for (Long roleId : roleIds) {
            UserRoleDo userRoleDo = new UserRoleDo();
            userRoleDo.setUserId(adminId);
            userRoleDo.setRoleId(roleId);
            userRoleDos.add(userRoleDo);
        }
        return userRoleMapper.insertBatch(userRoleDos);
    }
}
