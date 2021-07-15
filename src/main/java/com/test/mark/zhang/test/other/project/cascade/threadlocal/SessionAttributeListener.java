package com.test.mark.zhang.test.other.project.cascade.threadlocal;

import com.test.mark.zhang.test.other.project.cascade.session.User;
import org.junit.Test;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by mark
 * @Classname A
 * @Description TODO
 * @Date 2021/7/14 2:35 下午
 */
@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    //创建session时触发
    public void attributeAdded(HttpSessionBindingEvent event) {
        if ("userInfo".equals(event.getName())) {
            AgentThreadLocal.set((User) event.getValue());
        }
    }

    @Override
    //销毁session时触发
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if ("userInfo".equals(event.getName())) {
            AgentThreadLocal.remove();
        }
    }

    @Override
    //替换session时触发
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if ("userInfo".equals(event.getName())) {
            AgentThreadLocal.set((User) event.getValue());
        }
    }

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("zhang");
        list.add("zhang2");
        list.add("zhang3");
        System.out.println(list);
        list.add(1,"li");
        System.out.println(list);
    }
}

