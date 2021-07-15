package com.test.mark.zhang.test.other.project.cascade.aware;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author by mark
 * @Classname MyAware
 * @Description TODO
 * @Date 2021/7/12 6:03 下午
 */
@Configuration
public class MyAware implements EnvironmentAware {
    @Override
    public void setEnvironment(Environment environment) {
        //application.properties里面配置后,可以读取配置
        String projectName = environment.getProperty("project.name");
        System.out.println("projectName::::" + projectName);
    }
}
