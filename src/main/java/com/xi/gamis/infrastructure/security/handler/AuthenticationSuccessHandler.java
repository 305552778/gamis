package com.xi.gamis.infrastructure.security.handler;

import com.xi.gamis.dto.CommonResult;
import com.xi.gamis.dto.LoginToken;
import com.xi.gamis.infrastructure.utils.JwtUtil;
import com.xi.gamis.infrastructure.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @Autowired
    private JwtUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        ///////////////////////////////////////////////////////////////////////////////////////
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成令牌
        String accessToken = jwtTokenUtil.createToken(userDetails.getUsername());
        //生成刷新令牌，如果accessToken令牌失效，则使用refreshToken重新获取令牌（refreshToken过期时间必须大于accessToken）
        String refreshToken = jwtTokenUtil.refreshToken(accessToken);
        renderToken(response, LoginToken.builder().accessToken(accessToken).refreshToken(refreshToken).build());


    }
    /**
     * 渲染返回 token 数据,因为前端页面接收的都是Result对象，故使用application/json返回
     */
    public void renderToken(HttpServletResponse response, LoginToken token) throws IOException {
        ResponseUtil.out(response,new CommonResult(200,token,"ok!!"));
    }
}
