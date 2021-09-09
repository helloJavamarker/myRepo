package com.test.mark.zhang.cache.ehcache;

import com.test.mark.zhang.test.agency.wang.thread.classloader.concurrent.design_pattern.chapter5.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {

    //ehcache其实就是springCache的使用
    //@Cacheable 注解中，通过 key 属性来指定 key ，如上代码就表示只使用 id 作为缓存的 key ，如果对 key 有复杂的要求，可以自定义 keyGenerator
    //这里user没有id属性,可以制定name为key
    //@Cacheable(key = "#id")
    //这里点进去注解,其实是spring源码
    @Cacheable(key = "#username")
    public User getUserById(Integer id, String username) {
        System.out.println("getUserById");
        return getUserFromDBById(id);
    }

    private User getUserFromDBById(Integer id) {
        return new User("zhangsan","addr" + id,null);
    }

    @CachePut(key = "#user.name")
    public User updateUserById(User user) {
        return user;
    }

    @CacheEvict()
    public void deleteUserById(Integer id) {
        //在这里执行删除操作， 删除是去数据库中删除
    }

}