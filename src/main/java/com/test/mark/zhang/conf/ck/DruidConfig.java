package com.test.mark.zhang.conf.ck;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author by mark
 * @Classname DruidConfig
 * @Description TODO
 * @Date 2021/9/7 3:54 下午
 */
@Configuration
public class DruidConfig {
    @Resource
    private JdbcParamConfig jdbcParamConfig ;
//    @Bean
//    public DataSource dataSource() {
//        DruidDataSource datasource = new DruidDataSource();
////        datasource.setUrl(jdbcParamConfig.getUrl());
////        datasource.setDriverClassName(jdbcParamConfig.getDriverClassName());
////        datasource.setInitialSize(jdbcParamConfig.getInitialSize());
////        datasource.setMinIdle(jdbcParamConfig.getMinIdle());
////        datasource.setMaxActive(jdbcParamConfig.getMaxActive());
////        datasource.setMaxWait(jdbcParamConfig.getMaxWait());
//        return datasource;
//    }
}


