package com.test.mark.zhang.test.agency.shanggg.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author by mark
 * @Classname MyBeanPost
 * @Description
 * bean 的后置处理器，bean 生命周期有七步
 * （1）通过构造器创建 bean 实例（无参数构造）
 * （2）为 bean 的属性设置值和对其他 bean 引用（调用 set 方法）
 * （3）把 bean 实例传递 bean 后置处理器的方法 postProcessBeforeInitialization
 * （4）调用 bean 的初始化的方法（需要进行配置初始化的方法）
 * （5）把 bean 实例传递 bean 后置处理器的方法 postProcessAfterInitialization
 * （6）bean 可以使用了（对象获取到了）
 * （7）当容器关闭时候，调用 bean 的销毁的方法（需要进行配置销毁的方法）
 * @Date 2021/7/13 7:35 下午
 */
public class MyBeanPost  implements BeanPostProcessor {

    //<bean id="myBeanPost" class="com.atguigu.spring5.bean.MyBeanPost"></bean>

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("在初始化之前执行的方法");
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("在初始化之后执行的方法");
        return bean;
    }

    //--> Spring IOC容器实例化Bean
    //--> 调用BeanPostProcessor的postProcessBeforeInitialization方法
    //--> 调用bean实例的初始化方法
    //--> 调用BeanPostProcessor的postProcessAfterInitialization方法
    //
    //可以看到，Spring容器通过BeanPostProcessor给了我们一个机会对Spring管理的bean进行再加工。比如：我们可以修改bean的属性，
    // 可以给bean生成一个动态代理实例等等。一些Spring AOP的底层处理也是通过实现BeanPostProcessor来执行代理包装逻辑的。

}
