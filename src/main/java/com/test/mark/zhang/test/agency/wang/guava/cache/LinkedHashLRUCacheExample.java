package com.test.mark.zhang.test.agency.wang.guava.cache;

import java.util.HashMap;
import java.util.Map;

/***************************************
 * @author:Alex Wang
 * @Date:2017/11/13
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
public class LinkedHashLRUCacheExample {
    public static void main(String[] args) {
        LRUCache<String, String> cache = new LinkedHashLRUCache<>(3);
        cache.put("1", "1");
        cache.put("2", "2");
        cache.put("3", "3");
        System.out.println(cache);
        cache.put("4", "4");

        System.out.println(cache);

        System.out.println(cache.get("2"));
        System.out.println(cache);

        LRUCache<String, Map<String, String>> cache2 = new LinkedHashLRUCache<>(3);
//        Object put = new HashMap<>().put("1", "1");
        System.out.println(".....");
        Map<String, String> map1 = new HashMap<>();
        map1.put("1","1");
        cache2.put("5", map1);
        cache2.put("6", map1);
        cache2.put("7", map1);
        cache2.put("8", map1);
        System.out.println(cache2);

    }
}
