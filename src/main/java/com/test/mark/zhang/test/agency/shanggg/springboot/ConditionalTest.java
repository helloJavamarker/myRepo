package com.test.mark.zhang.test.agency.shanggg.springboot;

import com.test.mark.zhang.test.agency.shanggg.springboot.bean.Person;
import com.test.mark.zhang.test.agency.shanggg.springboot.bean.Pet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author by mark
 * @Classname ConditionalTest
 * @Description 满足某些条件的时候,才会创建这个bean
 * @Date 2021/11/27 8:37 下午
 */
@Configuration
public class ConditionalTest {

    @Bean
    @ConditionalOnBean(name = "tomcatCondition")
    // 当容器中有tomcatCondition这个组件的时候,再向容器中注入这个person组件,否则就不注入----这里是不是可以解决循环依赖
    public Person person(){
        Person person = new Person();
//        person.setPet(new Pet());
        person.setPet(pet());
        //这里true的时候是对的,调用pet()方法,会从容器中获取,FALSE的时候不会从容器调用,每次调用都会创建pet,相当于直接调用方法
        person.setAge(10);
        person.setName("zhangsan");
        return person;
    }

//    @Bean("tomcatCondition")
    public Pet pet() {
        Pet pet = new Pet();
        pet.setName("tomcat");
        return pet;
    }

}
