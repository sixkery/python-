package com.sixkery.kike.admin.config;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.sixkery.kike.admin.config.dto.PermissionDto;
import com.sixkery.kike.admin.config.dto.RoleDto;
import com.sixkery.kike.admin.config.vo.UserVo;
import com.sixkery.kike.admin.constant.SecurityConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 配置用户的角色和权限
 *
 * @author sixkery
 */
@Slf4j
public class SecurityUserDetails extends UserVo implements UserDetails {

    private List<PermissionDto> permissions;

    private List<RoleDto> roles;

    public SecurityUserDetails(UserVo user) {

        if (user != null) {
            // Principal用户信息
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setStatus(user.getStatus());


            this.permissions = user.getPermissionDos();
            this.roles = user.getRoleDos();
        }
    }

    /**
     * 添加用户拥有的权限和角色
     *
     * @return 权限和角色
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        // 添加权限
        if (CollUtil.isNotEmpty(permissions)) {
            for (PermissionDto permission : permissions) {
                if (StrUtil.isNotBlank(permission.getTitle()) && StrUtil.isNotBlank(permission.getPath())) {
                    authorityList.add(new SimpleGrantedAuthority(permission.getTitle()));
                }
            }
        }

        if (CollUtil.isNotEmpty(roles)) {
            roles.forEach(roleDto -> {
                if (StrUtil.isNotBlank(roleDto.getName())) {
                    authorityList.add(new SimpleGrantedAuthority(roleDto.getName()));
                }
            });
        }

        return authorityList;
    }


    /**
     * 是否过期
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用该用户
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return SecurityConstant.USER_STATUS_LOCK.equals(this.getStatus());
    }

    /**
     * 密码是否过期
     *
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * 是否启用
     *
     * @return boolean
     */
    @Override
    public boolean isEnabled() {

        return SecurityConstant.USER_STATUS_LOCK.equals(this.getStatus());
    }
}