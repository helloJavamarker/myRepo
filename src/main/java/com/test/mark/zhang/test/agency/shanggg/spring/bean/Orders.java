package com.test.mark.zhang.test.agency.shanggg.spring.bean;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author by mark
 * @Classname Bean
 * @Description 1、生命周期
 * （1）从对象创建到对象销毁的过程
 * 2、bean 生命周期
 * （1）通过构造器创建 bean 实例（无参数构造）
 * （2）为 bean 的属性设置值和对其他 bean 引用（调用 set 方法）
 * （3）调用 bean 的初始化的方法（需要进行配置初始化的方法）
 * （4）bean 可以使用了（对象获取到了）
 * （5）当容器关闭时候，调用 bean 的销毁的方法（需要进行配置销毁的方法）
 *
 * @Date 2021/7/13 7:30 下午
 */
public class Orders {
    //无参数构造
    public Orders() {
        System.out.println("第一步 执行无参数构造创建 bean 实例");
    }
    private String oname;
    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("第二步 调用 set 方法设置属性值");
    }
    //创建执行的初始化的方法
    public void initMethod() {
        System.out.println("第三步 执行初始化的方法");
    }
    //创建执行的销毁的方法
    public void destroyMethod() {
        System.out.println("第五步 执行销毁的方法");
    }

    //<bean id="orders" class="com.atguigu.spring5.bean.Orders" init-method="initMethod"
    // destroy-method="destroyMethod"> <property name="oname" value="手机"></property>
    //</bean>
    @Test
    public void testBean3() {
// ApplicationContext context =
// new ClassPathXmlApplicationContext("bean4.xml");
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("bean4.xml");
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println("第四步 获取创建 bean 实例对象");
        System.out.println(orders);
//手动让 bean 实例销毁
        context.close();
    }

}
