package com.xi.gamis.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SpringSecurityLogoutHandler  implements LogoutHandler {
    @Autowired
private TokenManager tokenManager;
    @Autowired
private RedisTemplate redisTemplate;

   /* public SpringSecurityLogoutHandler(TokenManager tokenManager, RedisTemplate redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }*/

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        //1.从head中去除token
        //2.token不为空，移除token，从redis删除token
        String token=httpServletRequest.getHeader("token");
        if(token!=null)
        {
            tokenManager.removeToken(token);
            String userName=tokenManager.getUsrInforFromToken(token);
            redisTemplate.delete(userName);
        }

    }
}
