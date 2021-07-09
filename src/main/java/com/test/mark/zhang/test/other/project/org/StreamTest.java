package com.test.mark.zhang.test.other.project.org;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname StreamTest
 * @Description TODO
 * @Date 2021/7/7 9:59 上午
 * @Created by mark
 */
public class StreamTest {
    public static void main(String[] args) {
        StreamTest streamTest = new StreamTest();
        streamTest.generateData();

    }

    private Map<String, List<String>> generateData() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list1 = Arrays.asList("zhang", "san");
        List<String> list2 = Arrays.asList("zhang1", "san1");
        List<String> list3 = Arrays.asList("zhang2", "san2");

        map.put("key1",list1);
        map.put("key1",list2);
        map.put("key1",list3);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("key1","value1");
        jsonObject.put("key1","value2");
        jsonObject.put("key3","value3");
        jsonObject.put("","empty");
        System.out.println(jsonObject);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("key1", jsonObject);
        jsonObject1.put("key2", "jsonObject");
        System.out.println(jsonObject1);
        //map.putIfAbsent()
        return map;

    }

}
