package com.xi.gamis.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xi.gamis.dto.CommonResult;
import com.xi.gamis.dto.UserInfo;
import com.xi.gamis.infrastructure.utils.ResponseUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

//注意：由于登录地址不是默认“/login”，所以需要继承AbstractAuthenticationProcessingFilter
public class TokenAuthenFilter extends  UsernamePasswordAuthenticationFilter {

    private TokenManager tokenManager;

    private RedisTemplate redisTemplate;

    private AuthenticationManager authenticationManager;

    public TokenAuthenFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.authenticationManager = authenticationManager;
        this.setPostOnly(true);
        //设置登录路径
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/login","POST"));
    }

    //1、获取表单用户名和密码
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //获取表单数据
        User user= new ObjectMapper().readValue(request.getInputStream(), User.class);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword(),new ArrayList<>()));

    }
    //认证成功调用方法
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User securityUser= (User)authResult.getPrincipal();
        //根据用户名生成TOKEN
        String toke=tokenManager.createToken(securityUser.getUsername());
        //把用户名称和用户权限放到redis中
        redisTemplate.opsForValue().set(securityUser.getUsername(),securityUser.getAuthorities());
        //返回token
        //ResponseUtil.out(response,new CommonResult(200,new HashMap<String,String>().put("token",toke),"ok"));
    }
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response,new CommonResult(0,"","认证失败"));
    }

}
