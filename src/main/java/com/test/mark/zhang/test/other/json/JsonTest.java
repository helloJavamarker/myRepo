package com.test.mark.zhang.test.other.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname JsonTest
 * @Description TODO
 * @Date 2021/7/9 10:51 上午
 * @Created by mark
 */
public class JsonTest {
    @Test
    public void jsonIterator() {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < 10; i++) {
            JSONObject content = new JSONObject();
            content.put("id:" + i, "name=>" + i);
            content.put("hobby:" + i, "play=>" + i * 100);
            jsonObject.put("id@ " + i, content);
        }
        System.out.println(jsonObject);

        //System.out.println(JSON.parseArray(jsonObject.toJSONString()));
        System.out.println(JSON.parseObject(jsonObject.toJSONString()));
        //System.out.println(JSON.parse(jsonObject.toJSONString()));
        //JSON.parseObject(jsonObject.toJSONString()).

    }

    @Test
    public void testStream() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("string" + i);
        }
        //list.stream().
    }


}
