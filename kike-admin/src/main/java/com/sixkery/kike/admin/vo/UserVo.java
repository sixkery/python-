package com.sixkery.kike.admin.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.sixkery.kike.admin.dto.PermissionDto;
import com.sixkery.kike.admin.dto.RoleDto;
import com.sixkery.kike.admin.entity.system.UserDo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author sixkery
 */
@Data
public class UserVo extends UserDo implements UserDetails {
    /**
     * 权限
     */
    private List<PermissionDto> permissionDos;

    /**
     * 角色
     */
    private List<RoleDto> roleDos;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        // 添加权限
        if (CollUtil.isNotEmpty(permissionDos)) {
            for (PermissionDto permission : permissionDos) {
                if (StrUtil.isNotBlank(permission.getTitle()) && StrUtil.isNotBlank(permission.getPath())) {
                    authorityList.add(new SimpleGrantedAuthority(permission.getTitle()));
                }
            }
        }

        if (CollUtil.isNotEmpty(roleDos)) {
            roleDos.forEach(roleDto -> {
                if (StrUtil.isNotBlank(roleDto.getName())) {
                    authorityList.add(new SimpleGrantedAuthority(roleDto.getName()));
                }
            });
        }

        return authorityList;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     *
     * @return 是否
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
