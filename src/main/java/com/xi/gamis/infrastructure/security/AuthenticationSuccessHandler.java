package com.xi.gamis.infrastructure.security;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xi.gamis.dto.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RedisTemplate stringRedisTemplate;
    @Autowired
    private TokenManager tokenManager;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        Object o=authentication.getPrincipal();
        //这里要在UserAuthenticationService类中设置user类型
        //SecurityUser user = (SecurityUser) authentication.getPrincipal();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        //根据用户名生成TOKEN
        String toke=tokenManager.createToken(user.getUsername());
        //把用户名称和用户权限放到redis中
        stringRedisTemplate.opsForValue().set(user.getUsername(), JSON.toJSONString(user.getAuthorities()),60, TimeUnit.MINUTES);
        //stringRedisTemplate.opsForValue().set("TOKEN:" + token, JSON.toJSONString(user), 60, TimeUnit.MINUTES);

        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(new CommonResult<>(1, "success", toke)));
        writer.flush();
        writer.close();
    }
}
