package com.test.mark.zhang.util.collection;

import com.google.common.collect.Maps;
import com.test.mark.zhang.test.other.project.security.SecurityZone;
import org.junit.Test;
import org.quartz.spi.ThreadExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Classname MapTest
 * @Description TODO
 * @Date 2021/6/2 4:42 下午
 * @Created by mark
 */
public class MapTest {
    public static void main(String[] args) {
        // 一: 将map换成concurrentHashMap的时候,需要注意concurrentHashMap的key和value都不能为null
        // 大佬直接意见不同,map的作者认为null没事,dog lea认为null是不定时炸弹

        // 二: 使用自定义key的时候,假如希望某些字段相同,就认为是同一个对象,来覆盖旧值,那就一定要重写hashCode和equals方法

        // 三:concurrentHashMap可以保证单个步骤线程安全,但是分开不能保证
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        Integer key = map.get("key");
        map.put("key", 1);
        //单步都是线程安全的,但是放到一起不是线程安全的    看业务可以使用原子类

        // 四: list的sublist会相互影响,map的三个方法也会相互影响
        // keySet()；values();entrySet();这三个方法创建返回新集合，底层其实都依赖的原有 Map 中数据，所以一旦 Map 中元素变动，就会同步影响返回的集合。
        //另外这三个方法返回新集合，是不支持的新增以及修改操作的，但是却支持 clear、remove 等操作。
        //不想相互影响,可以套娃一下:new ArrayList<>(map.values());


        // 五: 使用collect.toMap的时候,null和重复问题


        Map<String, SecurityZone> securityZoneMap = generateData(10);
        Map<String, String> idNetworkMap = securityZoneMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getNetwork()));
        System.out.println("idNetworkMap = " + idNetworkMap);

        securityZoneMap.forEach((id, securityZone)-> securityZone.setName("after:"));
        System.out.println("securityZoneMap = " + securityZoneMap);

    }

    private static Map<String, SecurityZone> generateData(int num) {
        Map<String, SecurityZone> map = new HashMap<>();
        for (int i = 0; i < num; i++) {
            SecurityZone securityZone = new SecurityZone();
            securityZone.setId("id:" + i);
            securityZone.setName("name:" + i);
            securityZone.setIconPath("path:" + i);
            securityZone.setNetwork("network:" + i);
            map.put("id:" + i, securityZone);
        }
        return map;
    }

    @Test
    public void test01() {
        Map<String, String> map = new HashMap<>();
        map.put("zhang1", "san1");
        map.put("zhang2", "san2");
        map.put("zhang3", "san3");
        map.put("zhang4", "san4");
        map.put("zhang5", "san5");
        map.put("zhang6", "san6");
        map.put("zhang7", "san7");
        map.put("zhang8", "san8");

    }
}
