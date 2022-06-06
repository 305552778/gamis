package com.xi.gamis.infrastructure.security;

import com.xi.gamis.dto.UserInfo;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {
    //使用transient关键字不序列化某个变量
    // *        注意读取的时候，读取数据的顺序一定要和存放数据的顺序保持一致
    //当前用户
    private transient UserInfo userInfo;
    //当前权限
    private List<String> permissionList;
    public SecurityUser()
    {

    }
    public SecurityUser(UserInfo userInfo) {
        if (userInfo!=null)
        {
            this.userInfo = userInfo;
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        for (String permission:permissionList)
        {
            if(StringUtils.isEmpty(permission))
            {
                SimpleGrantedAuthority authority=new SimpleGrantedAuthority(permission);
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUserName();
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
        return true;
    }
}
