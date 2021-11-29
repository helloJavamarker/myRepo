package com.test.mark.zhang.test.agency.shanggg.springboot;

import com.test.mark.zhang.test.agency.shanggg.springboot.bean.Person;
import com.test.mark.zhang.test.agency.shanggg.springboot.bean.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by mark
 * @Classname ConfigurationTest
 * @Description TODO
 * @Date 2021/11/27 8:19 下午
 */
@Configuration(proxyBeanMethods=true)
// 弹幕说:configuration和component区别是,前者默认是cglib
public class ConfigurationTest {

    /**
     * proxyBeanMethods=ture表示springboot开始的是full模式,每次调用这里的方法,会先去容器中看是不是bean已经注入
     * proxyBeanMethods=false为lite模式,不会检查,可以加快启动速度
     *
     * --总之看是不是有组件依赖
     * 最佳实践:
     *  类组件之间没有依赖关系使用lite模式,加快容器启动速度,减少判断
     *  有依赖关系,使用full,默认是full
     * @return
     */

    @Bean
    public Person person(){
        Person person = new Person();
//        person.setPet(new Pet());
        person.setPet(pet());
        //这里true的时候是对的,调用pet()方法,会从容器中获取,FALSE的时候不会从容器调用,每次调用都会创建pet,相当于直接调用方法
        person.setAge(10);
        person.setName("zhangsan");
        return person;
    }

    @Bean
    public Pet pet() {
        Pet pet = new Pet();
        pet.setName("tomcat");
        return pet;
    }


}
