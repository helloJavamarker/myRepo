package com.test.mark.zhang.test.agency.shanggg.springboot.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author by mark
 * @Classname ConfigurationPropertiesBean
 * @Description TODO
 * @Date 2021/11/27 8:55 下午
 */
@Data
@Component// 必须要先假如到容器中
@ConfigurationProperties(prefix = "my")//这里并没有写配置文件的位置,这里默认是application.properties
public class ConfigurationPropertiesBean {

    private String name;
    private String value;
    private int age;
}
