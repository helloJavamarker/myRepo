package com.test.mark.zhang.test.agency.shanggg.springboot;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author by mark
 * @Classname ConfigurationPropertiesTest
 * @Description TODO
 * @Date 2021/11/27 8:49 下午
 */
//@EnableConfigurationProperties 绑定属性
public class ConfigurationPropertiesTest {

    /**
     * 不使用springboot读取配置文件,并封装到javabean中
     * @throws IOException
     */
    public void readProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("a.properties")); //得到配置文件名字
        Enumeration<?> enumeration = properties.propertyNames();
        while (enumeration.hasMoreElements()) {
            String strKey = (String) enumeration.nextElement();
            String strValue = properties.getProperty(strKey);
            System.out.println(strKey + ":" + strValue);
            //封装到javabean
        }
    }
}
