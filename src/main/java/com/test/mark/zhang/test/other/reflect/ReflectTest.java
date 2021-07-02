package com.test.mark.zhang.test.other.reflect;

import com.test.mark.zhang.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @Classname ReflectTest
 * @Description TODO
 * @Date 2021/6/19 10:04 上午
 * @Created by mark
 */
@Slf4j
@RestController
@RequestMapping("test")
public class ReflectTest {
    /**
     * 运行中获取,改变方法,属性,动态代理
     *
     * 反射的灵魂是"反"
     */

    public static void main(String[] args) throws Exception {
        HashMap<String, Person> map = new HashMap<>();
        map.put("zhang",new Person());
        map.put("zhang2",new Person("zhang",23,"li",100L));
        log.info("error:{}",Arrays.asList(new Person(),new Person("zhang",23,"li",100L),new Person()));
        log.info("error:{}",map);

        try {
            int a = 10 /0;
        } catch (Exception e) {
            //int  a = 10 /0;
            log.error("error:",e);
            int b =2/0;
        }

        //所谓"正射",是我们实现知道这个类是干啥的,所以可以直接new,再直接调用
        Apple apple = new Apple(); //直接初始化，「正射」
        apple.setPrice(4);
        System.out.println(apple);

        //"反射",是事先不知道这个类和这个对象是干啥的,
        Class clz = Class.forName("com.test.mark.zhang.test.other.reflect.Apple");
        Method method = clz.getMethod("setPrice", int.class);
        Constructor constructor = clz.getConstructor();
        Object object = constructor.newInstance();
        method.invoke(object, 14);
        System.out.println(object);

        //上面两段代码的执行结果，其实是完全一样的。但是其思路完全不一样，第一段代码在未运行时就已经确定了要运行的类（Apple），
        // 而第二段代码则是在运行时通过字符串值才得知要运行的类（com.chenshuyi.reflect.Apple）。
    }

    @GetMapping("reflect")
    public String test(@RequestParam("name") String name) throws Exception {

        //通过外接传入字符串来调用不同方法,在这里看起来并不是很好的方式,因为可以通过其他形式实现,而且只能调用update和search方法,不对会抛错
        //java.lang.NoSuchMethodException
        Class clazz = this.getClass();
        //这个name就是要调用的方法名
        Method method = clazz.getDeclaredMethod(name, Person.class, Integer.class);
        method.invoke(this, new Person(), 1);
        return "success";
    }


    private Person add() {
        System.out.println("test01");
        return new Person("zhang",23,"eat",100L);
    }

    private Person update(Person person, Integer age) {
        System.out.println("test02");
        System.out.println(person);
        return new Person("li",age,"play",200L);
    }

    private Person search(Person person,Integer age) {
        System.out.println("test03");
        return new Person("zhao",age,"sing",300L);
    }

}
