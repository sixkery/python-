package com.sixkery.kike.admin.configuration.security;

import com.sixkery.kike.admin.entity.system.ResourceDo;
import com.sixkery.kike.admin.entity.system.UserDo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: sixkery
 * @date:2021/5/7
 */
public class AdminUserDetails implements UserDetails {
    private UserDo userDo;
    private List<ResourceDo> resourceDoList;

    public AdminUserDetails(UserDo userDo, List<ResourceDo> resourceDoList) {
        this.userDo = userDo;
        this.resourceDoList = resourceDoList;
    }

    /**
     * 返回当前用户拥有的资源
     *
     * @return 资源
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return resourceDoList.stream().map(resourceDo ->
                new SimpleGrantedAuthority(resourceDo.getId() + ":" + resourceDo.getName())
        ).collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return userDo.getPassword();
    }

    @Override
    public String getUsername() {
        return userDo.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userDo.getStatus().equals(1);
    }
}
