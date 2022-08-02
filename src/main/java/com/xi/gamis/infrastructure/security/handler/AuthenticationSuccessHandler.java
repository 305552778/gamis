package com.xi.gamis.infrastructure.security.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xi.gamis.dto.CommonResult;
import com.xi.gamis.infrastructure.security.TokenManager;
import com.xi.gamis.infrastructure.utils.RedisUtil;
import com.xi.gamis.infrastructure.utils.ResponseUtil;
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
/**
 * 为什么不实现接口，而是继承SavedRequestAwareAuthenticationSuccessHandler类的方式？
 * 因为SavedRequestAwareAuthenticationSuccessHandler这个类记住了你上一次的请求路径，比如：
 * 你请求user.html。然后被拦截到了登录页，这时候你输入完用户名密码点击登录，会自动跳转到user.html，而不是主页面。
 *
 * 若是前后分离项目则实现接口即可，因为我弄的是通用的权限组件，所以选择了继承
 */
@Component
//public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    //private RedisTemplate stringRedisTemplate;
    private RedisUtil redisUtil;
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
        //stringRedisTemplate.opsForValue().set(user.getUsername(), JSON.toJSONString(user.getAuthorities()),60, TimeUnit.MINUTES);
        redisUtil.set(user.getUsername(),toke,60*60);
        //stringRedisTemplate.opsForValue().set("TOKEN:" + token, JSON.toJSONString(user), 60, TimeUnit.MINUTES);
        ResponseUtil.out(response,new CommonResult(1, toke,"success"));

        /*response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(new CommonResult<>(1, "success", toke)));
        writer.flush();
        writer.close();*/
    }
}
