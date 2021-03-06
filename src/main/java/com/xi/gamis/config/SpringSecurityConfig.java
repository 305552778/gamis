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
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.headers().frameOptions().disable();
        http.csrf().disable().headers().frameOptions().sameOrigin();// ??????iframe????????????;//??????????????????????????????????????????????????????????????????????????????BUG?????????????????????????????????FK!!
        http.logout()
                .logoutUrl("/logout").invalidateHttpSession(true)
                .logoutSuccessUrl("/test.html").permitAll();
        //http.exceptionHandling().accessDeniedPage("/common/failure").authenticationEntryPoint(new UnAuthEntryPoint());//?????????????????????????????????
        http.formLogin()
                //.successHandler(authenticationSuccessHandler)
                //.failureHandler(myAuthenticationFailureHandler)
                .defaultSuccessUrl("/view/life",true)
                .usernameParameter("username")
                .passwordParameter("password")
                .loginPage("/auth/login")//?????????????????????
                .loginProcessingUrl("/view/index")//????????????controller,?????????????????????????????????????????????
                //.successForwardUrl("/view/life")
                .successHandler(authenticationSuccessHandler)
                .permitAll()

                .permitAll()
                .failureUrl("/common/failure")


        .and()
        .authorizeRequests()
        .antMatchers("/login.html","**.css","**.js","/auth/login","/common/failure").permitAll()//????????????????????????
        //.antMatchers("/view/life").hasAuthority("p345e")//??????????????????????????????????????????????????????controller?????????
        //.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
        .anyRequest().authenticated()//?????????????????????
        .and()
        .rememberMe()
        .tokenRepository(persistentTokenRepository())//????????????
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
    }
    @Bean
    MyUsernamePasswordAuthenticationFilter myAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(new CommonResult(403,"","????????????")));
            }
        });
        return filter;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository()
    {

        JdbcTokenRepositoryImpl jdbcTokenRepository=new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);//??????????????? 'persistent_logins' ?????????
        return  jdbcTokenRepository;
    }

    @Bean
    PasswordEncoder password()
    {
        return new BCryptPasswordEncoder();
    }
    //??????????????????????????????????????????
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login.html");
    }
}
