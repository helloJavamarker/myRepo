package com.test.mark.zhang.test.other.project.cascade.anno.generic;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by mark
 * @Classname GenericTest
 * @Description TODO
 * @Date 2021/7/11 8:00 下午
 */
public class GenericTest{


    /**
     * 这里<T> T 可以传入任何类型的list
     * 参数T
     *  第一个,表示是泛型
     *  第二个,表示返回的类型是T类型的数据
     *  第三个,限制参数类型为T
     *      ps:T表示一个占位符,用来告诉编译器,这个东西先留着,等我编译的时候,告诉你
     * @param data
     * @param <T>
     * @return
     */
    private <T> T get(List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            return null;
        }
        return data.get(0);
    }


    private void test1() {
        //public interface List<E> extends Collection<E>     这里<E>类似一个形参,传入的类型是什么,list里面就是什么  类型参数化
        List<String> list = new ArrayList<>();
        List<Integer> ints = new ArrayList<>();
        list.add("zhang");
        list.add("san");
        String result = get(list);

    }

}
