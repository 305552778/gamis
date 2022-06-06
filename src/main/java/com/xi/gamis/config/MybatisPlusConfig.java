package com.xi.gamis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.xi.gamis.mapper")
public class MybatisPlusConfig {

    // 旧版
    //@Bean
    //public PaginationInterceptor paginationInterceptor() {
        //PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        //paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        //return paginationInterceptor;
    //}
    // 最新版
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.SQL_SERVER));
        return interceptor;
    }
}
