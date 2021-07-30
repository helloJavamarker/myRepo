package com.test.mark.zhang.util.hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.lang.Console;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * @author by mark
 * @Classname IOTest
 * @Description TODO
 * @Date 2021/7/19 7:29 下午
 */
public class IOTest {

    //在Hutool中，WatchMonitor主要针对JDK7中WatchService做了封装，针对文件和目录的变动（创建、更新、删除）做一个钩子，在Watcher中定义相应的逻辑来应对这些文件的变化。
    //监听文件变化
    @Test
    public void fileListener() {
        /*WatchMonitor提供的事件有：

        ENTRY_MODIFY 文件修改的事件
        ENTRY_CREATE 文件或目录创建的事件
        ENTRY_DELETE 文件或目录删除的事件
        OVERFLOW 丢失的事件*/

    }

    @Test
    public void json2() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("zhang","san");
        map1.put("zhang2","san2");
        map1.put("zhang3","san3");
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("content", JSONObject.toJSONString(map1));
        jsonObject1.put("action", true);
        System.out.println(jsonObject1);

        JSONObject json = new JSONObject();
        json.put("zhang","san");
        json.put("zhang2","san2");
        json.put("zhang3","san3");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("content", json);
        jsonObject.put("action", true);
        System.out.println(jsonObject);

        //json里面放map和放json对象不同
        Map<String, Object> map = new HashMap<>();
        map.put("zhang","san");
        map.put("zhang2","san2");
        map.put("zhang3","san3");
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("content", JSONObject.toJSONString(map));
        jsonObject2.put("action", true);
        System.out.println(jsonObject2.equals(jsonObject));

        JSONObject person = new JSONObject();
        person.put("action", true);
        person.put("content", new Person("zhang","san"));
        System.out.println(person);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Person {
        String firstName;
        String lastName;
    }

    //监听对文件的操作
    @Test
    public void listener() {
        File file = FileUtil.file("example.properties");
        //这里只监听文件或目录的修改事件
        WatchMonitor watchMonitor = WatchMonitor.create(file, WatchMonitor.ENTRY_MODIFY);
        watchMonitor.setWatcher(new Watcher(){
            @Override
            public void onCreate(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("创建：{}-> {}", currentPath, obj);
            }

            @Override
            public void onModify(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("修改：{}-> {}", currentPath, obj);
            }

            @Override
            public void onDelete(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("删除：{}-> {}", currentPath, obj);
            }

            @Override
            public void onOverflow(WatchEvent<?> event, Path currentPath) {
                Object obj = event.context();
                Console.log("Overflow：{}-> {}", currentPath, obj);
            }
        });

        //设置监听目录的最大深入，目录层级大于制定层级的变更将不被监听，默认只监听当前层级目录
        watchMonitor.setMaxDepth(3);
        //启动监听
        watchMonitor.start();

    }
}
