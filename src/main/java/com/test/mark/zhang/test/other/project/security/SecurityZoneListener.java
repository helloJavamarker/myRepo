package com.test.mark.zhang.test.other.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Classname SecurityZoneListener
 * @Description TODO
 * @Date 2021/6/25 3:20 下午
 * @Created by mark
 */
//@Component //要不要加?
public class SecurityZoneListener implements ApplicationListener<SecurityZoneEvent> {
    //事件,监听,通知,干事   基于观察者模式

    /**
     * Spring 提供了以下的标准事件：
     * 1	ContextRefreshedEvent ApplicationContext 被初始化或刷新时，该事件被发布。这也可以在 ConfigurableApplicationContext 接口中使用 refresh() 方法来发生。
     * 2	ContextStartedEvent当使用 ConfigurableApplicationContext 接口中的 start() 方法启动 ApplicationContext 时，该事件被发布。你可以调查你的数据库，或者你可以在接受到这个事件后重启任何停止的应用程序。
     * 3	ContextStoppedEvent当使用 ConfigurableApplicationContext 接口中的 stop() 方法停止 ApplicationContext 时，发布这个事件。你可以在接受到这个事件后做必要的清理的工作。
     * 4	ContextClosedEvent当使用 ConfigurableApplicationContext 接口中的 close() 方法关闭 ApplicationContext 时，该事件被发布。一个已关闭的上下文到达生命周期末端；它不能被刷新或重启。
     * 5	RequestHandledEvent这是一个 web-specific 事件，告诉所有 bean HTTP 请求已经被服务
     */
    @Autowired
    private SecurityZoneService securityZoneService;
    @Override
    public void onApplicationEvent(SecurityZoneEvent securityZoneEvent) {
        securityZoneService.add();
    }
}
