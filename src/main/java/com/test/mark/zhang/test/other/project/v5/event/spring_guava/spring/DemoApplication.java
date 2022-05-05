package com.test.mark.zhang.test.other.project.v5.event.spring_guava.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//执行类，测试入口
@SpringBootApplication
//@ComponentScan(basePackages = {"com.test.mark.zhang.test.other.project.v5.event.spring_guava.spring"})
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		String[] names = context.getBeanDefinitionNames();
		for(int i=0; i<names.length; i++) {
			System.out.println(names[i]);
		}
		System.out.println("++++++++++");
		context.publishEvent(new HelloEvent("helloEvent"));
		context.publishEvent(new CustomerEvent("customer", true));
		context.publishEvent(new CustomerEvent("miaomiao", false));

		//result
        ////以下是spring上下文event，继承自 ApplicationContextEvent。 用于用户参与上下文生命周期的入口。因为是ApplicationEvent子类型，所以，由processApplicationEvent处理。
        //process common event, class:ContextRefreshedEvent
        //process common event, class:EmbeddedServletContainerInitializedEvent
        //process common event, class:ApplicationReadyEvent
        //process common event, class:ContextRefreshedEvent
        ////以下是上下文中的bean
        //springListener
        //org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        //org.springframework.context.annotation.internalAutowiredAnnotationProcessor
        //org.springframework.context.annotation.internalRequiredAnnotationProcessor
        //org.springframework.context.annotation.internalCommonAnnotationProcessor
        //org.springframework.context.event.internalEventListenerProcessor
        //org.springframework.context.event.internalEventListenerFactory
        //++++++++++
        ////HelloEvent 继承 ApplicationEvent，会被processApplicationEvent处理
        //process common event, class:HelloEvent
        ////监听 HelloEvent类型 的 processHelloEvent 处理
        //process helloEvent, name:helloEvent
        ////非 ApplicationEvent 的事件，则为 PayloadApplicationEvent
        //process common event, class:PayloadApplicationEvent
        ////isCustomer=true,符合processCustomerEvent处理条件
        //process customer CustomerEvent, name:customer
        ////监听CustomerEvent类型，处理结果
        //Async process CustomerEvent, name:customer
        //process common event, class:PayloadApplicationEvent
        ////符合processMiaoMiaoEvent条件
        //process miaomiao's CustomerEvent, name:miaomiao
        //Async process CustomerEvent, name:miaomiao
        ////spring 上下文事件
        //process common event, class:ContextClosedEvent

	}
}