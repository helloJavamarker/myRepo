package com.test.mark.zhang.test.other.project.cascade.threadlocal;

import com.test.mark.zhang.test.other.project.cascade.session.User;

/**
 * @author by mark
 * @Classname A
 * @Description TODO
 * @Date 2021/7/14 2:31 下午
 */
public class AgentThreadLocal {
    private AgentThreadLocal(){
    }
    //ConsoleUserVo是存储用户信息的实体类我就不说了
    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void set(User user){
        LOCAL.set(user);
    }

    public static User get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }

}
