package com.test.mark.zhang.test.agency.wang.guava.test;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static junit.framework.Assert.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/***************************************
 * @author:Alex Wang
 * @Date:2018/1/15
 * QQ: 532500648
 * QQ群:463962286
 ***************************************/
@Slf4j(topic = "immutableCollection")
public class ImmutableCollectionsTest {

    @Test(expected = UnsupportedOperationException.class)
    public void testOf() {
        ImmutableList<Integer> list = ImmutableList.of(1, 2, 3);
        assertThat(list, notNullValue());
        list.add(4);
        fail();
    }

    @Test
    public void testObject() {

        List<String> list = Arrays.asList("zhang", "san", "li", "si", "wang", "wu", "zhao", "liu");
        System.out.println(list.stream().collect(Collectors.joining(",..","--","----")));
        log.debug(list.stream().collect(Collectors.joining(",..","--","----")));
        System.out.println(Math.ceil(list.size() / 5.0));
//        for (int i = 0; i < Math.ceil(list.size() / 5.0); i++) {
//            System.out.println(i);
//
//        }
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("hello", "world");
//        jsonObject.put("world", "haha");
//        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
//            System.out.println(entry.getValue());
//        }
    }

    @Test
    public void testCopy() {
        //为什么要用immutable对象？immutable对象有以下的优点：
        //　　　　1.对不可靠的客户代码库来说，它使用安全，可以在未受信任的类库中安全的使用这些对象
        //　　　　2.线程安全的：immutable对象在多线程下安全，没有竞态条件
        //　　　　3.不需要支持可变性, 可以尽量节省空间和时间的开销. 所有的不可变集合实现都比可变集合更加有效的利用内存 (analysis)
        //　　　　4.可以被使用为一个常量，并且期望在未来也是保持不变的
        Integer[] array = {1, 2, 3, 4, 5};
        System.out.println(ImmutableList.copyOf(array));
        // public static <E> ImmutableList<E> copyOf(E[] elements) {
        //        switch(elements.length) {
        //        case 0:
        //            return of();
        //        case 1:
        //            return of(elements[0]);
        //        default:
        //            return construct((Object[])elements.clone());
        //        }
        //    }
    }

    @Test
    public void testBuilder() {
        ImmutableList<Integer> list = ImmutableList.<Integer>builder()
                .add(1)
                .add(2, 3, 4).addAll(Arrays.asList(5, 6))
                .build();
        System.out.println(list);
    }

    @Test
    public void testImmutableMap() {
        ImmutableMap<String, String> map = ImmutableMap.<String, String>builder().put("Oracle", "12c")
                .put("Mysql", "7.0").build();
        System.out.println(map);
        try {
            map.put("Scala", "2.3.0");
            fail();
        } catch (Exception e) {
            assertThat(e instanceof UnsupportedOperationException, is(true));
        }
    }
}