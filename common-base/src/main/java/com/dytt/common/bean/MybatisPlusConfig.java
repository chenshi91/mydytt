package com.dytt.common.bean;

/**
 * @author chenshi
 * @date 2019-05-27
 */

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        return new MybatisSqlSessionFactoryBean(){{
            setDataSource(dataSource);
            setPlugins(new Interceptor[]{paginationInterceptor()});
        }};
    }

    /**
     * mybatis-plus分页插件
     *
     * @return
     */
    private PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor(){{
            setDialectType("mysql");
        }};
    }

}
