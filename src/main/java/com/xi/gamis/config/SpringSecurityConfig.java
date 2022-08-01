package com.xi.gamis.config;

import com.alibaba.fastjson.JSON;
import com.xi.gamis.application.UserAuthenticationService;
import com.xi.gamis.dto.CommonResult;
import com.xi.gamis.infrastructure.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class  SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    //private UserDetailsService userDetailsService;
    private UserAuthenticationService userDetailsService;
    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    DataSource  dataSource;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(password());
    }

    ///https://blog.csdn.net/qq_21602341/article/details/114577740
    ///前后端分离实现API认证
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable();
        http.csrf().disable().headers().frameOptions().sameOrigin();// 解决iframe无法访问;//这个设置一定要放在最前面！！老子快崩溃了！！找了一天BUG，就是这个顺序的原因！FK!!
        http.authorizeRequests().antMatchers("/auth").anonymous().anyRequest().authenticated();
    }

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable().headers().frameOptions().sameOrigin();// 解决iframe无法访问;//这个设置一定要放在最前面！！老子快崩溃了！！找了一天BUG，就是这个顺序的原因！FK!!
        http.logout()
                .logoutUrl("/logout").invalidateHttpSession(true)
                .logoutSuccessUrl("/test.html").permitAll();
        http.exceptionHandling().authenticationEntryPoint(new UnAuthEntryPoint());//匿名用户访问无权限资源时的异常处理
        //http.exceptionHandling().accessDeniedPage("/common/failure").authenticationEntryPoint(new UnAuthEntryPoint());//设置无权限访问跳转页面
        http.formLogin()
                //.successHandler(authenticationSuccessHandler)
                //.failureHandler(myAuthenticationFailureHandler)
                .defaultSuccessUrl("/view/life",true)
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/auth/login")//自定义登录页面
                .loginProcessingUrl("/view/index")//登录表单controller,如果有自定义过滤器，路径要相同
                //.successForwardUrl("/view/life")
                .successHandler(authenticationSuccessHandler)
                .permitAll()

                .permitAll()
                .failureUrl("/common/failure")


        .and()
        .authorizeRequests()
        .antMatchers("/login.html","**.css","**.js","/auth/login","/common/failure").permitAll()//哪些路径不要认证
        //.antMatchers("/view/life").hasAuthority("p345e")//配置访问路径所需权限，一般通过注解在controller中配置
        //.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
        .anyRequest().authenticated()//所有请求要认证
        .and()
        .rememberMe()
        .tokenRepository(persistentTokenRepository())//一般不用
        .tokenValiditySeconds(60)//.userDetailsService(userDetailsService)
        .and()
        //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //.maximumSessions(1)
        //.maxSessionsPreventsLogin(false)
        //.expiredSessionStrategy(new MyExpiredSessionStrategy());
        ;//.httpBasic()
        //http.addFilter(new TokenAuthenFilter(authenticationManager(),tokenManager,redisTemplate));
        //http.addFilter(new TokenAuthorityFilter(authenticationManager())).httpBasic();

        http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        //http.addFilterAt(new TokenAuthenFilter(authenticationManager(),tokenManager,redisTemplate), UsernamePasswordAuthenticationFilter.class);
        //http.addFilterBefore(new TokenAuthorityFilter(authenticationManager()), BasicAuthenticationFilter.class);
        //.httpBasic().disable();
        ;
    }*/
    @Bean
    MyUsernamePasswordAuthenticationFilter myAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(new CommonResult(403,"","登陆失败")));
            }
        });
        return filter;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository()
    {

        JdbcTokenRepositoryImpl jdbcTokenRepository=new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);//数据库创建 'persistent_logins' 的对象
        return  jdbcTokenRepository;
    }

    @Bean
    PasswordEncoder password()
    {
        return new BCryptPasswordEncoder();
    }
    //不进行认证的路径，可直接访问
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login.html");
    }
}
