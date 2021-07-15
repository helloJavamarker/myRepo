package com.test.mark.zhang.test.other.project.cascade.anno.reflection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname ReflectionTest
 * @Description java是一个静态语言,运行时结构不可变,但是java通过反射,具有一定的动态性
 *  动态语言
 *      比如下面的js代码
 *      function f() {
 *          var x = "var a = 3; var b = 5; alert(a + b)";
 *          //这里x先是一个字符串
 *          eval(x);
 *          //运行时,可以变成可执行的脚本     string变成了数字
 *      }
 *  主要的动态语言
 *      object-c,c#,javascript,php,python等
 *  主要静态语言
 *      java,c,c++等
 *
 *  反射允许程序在执行期间通过反射api,获取任何类的内部信息,并能直接操作任意对象的内部属性及其方法
 *  加载完类后,在堆内存方法区就产生了一个class类型的对象(一个类只有一个class对象),这个对象就包含了完整的类的结构信息---通过这个对象的结构,可以看到类结构
 *  "正"表示通过类获取对象     "反"表示通过对象获取类的结构
 * @Date 2021/7/11 9:12 上午
 * @Created by mark
 */
public class ReflectionTest1 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> personClass = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.reflection.Person1");
        //Class<Person> personClass2 = (Person)Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.reflection.Person");
        Class personClass2 = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.reflection.Person1");
        Class<?> personClass3 = Class.forName("com.test.mark.zhang.test.other.project.cascade.anno.reflection.Person1");
        System.out.println(personClass3);
        System.out.println(personClass2.hashCode());
        System.out.println(personClass.hashCode());
        //获得的类是唯一的----也算是单例??
        //Class对象只能由系统创建,我们只是拿到这个对象,通过这个对象做事  一个CLass对象对应的是加载到jvm中的一个.class文件
        //Class是reflection的根源,针对任何想动态加载,运行的类,唯有先获得响应的Class对象
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person1 {
    private String name;
    private int age;
}
