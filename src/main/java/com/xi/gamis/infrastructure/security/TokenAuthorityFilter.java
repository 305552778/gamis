package com.xi.gamis.infrastructure.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TokenAuthorityFilter extends BasicAuthenticationFilter {


    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    @Autowired
    public TokenAuthorityFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        //获取认证成功权限信息
        UsernamePasswordAuthenticationToken authRequest =  getAuthentication(request);
        if(authRequest!=null)
        {
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }
        chain.doFilter(request,response);
    }
    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request)
    {
       String token= request.getHeader("token");
       if(!StringUtils.isEmpty(token))
       {
         String userName=  tokenManager.getUsrInforFromToken(token);
         List<String> permissionList= (List<String> )redisTemplate.opsForValue().get(userName);
         Collection<GrantedAuthority> authorities=new ArrayList<>();
         for (String permission:permissionList)
         {
             if(StringUtils.isEmpty(permission))
             {
                 SimpleGrantedAuthority authority=new SimpleGrantedAuthority(permission);
                 authorities.add(authority);
             }
         }
        return new UsernamePasswordAuthenticationToken(userName,token,authorities);
       }
        return null;
    }

}
