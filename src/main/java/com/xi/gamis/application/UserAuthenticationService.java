package com.xi.gamis.application;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.xi.gamis.infrastructure.security.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority> aut= AuthorityUtils.commaSeparatedStringToAuthorityList("p341e,p342e,ROLE_admin");
        return new User("gq1",new BCryptPasswordEncoder().encode("123"),aut);
        //BeanUtils.copyProperties();
        //或者返回 return new SecurityUser();
    }
}
