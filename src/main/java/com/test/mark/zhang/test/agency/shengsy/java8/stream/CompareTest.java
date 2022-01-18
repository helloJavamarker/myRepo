package com.test.mark.zhang.test.agency.shengsy.java8.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by mark
 * @Classname CompareTest
 * @Description TODO
 * @Date 2021/12/12 10:18 下午
 */
public class CompareTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "verygood");

        //静态方法,按字母顺序排序,相同比较下一个字母
        Collections.sort(list);
        System.out.println("list = " + list);

        Collections.sort(list, (item1, item2) -> item1.length() - item2.length());
        Collections.sort(list, (item1, item2) -> item2.length() - item1.length());
        Collections.sort(list, Comparator.comparingInt(String::length).reversed());

//        Collections.sort(list, Comparator.comparingInt(item -> item.length()).reversed());
//        这里item并不能被识别为string类型的数据,不能通过类型推断得到类型
        Collections.sort(list, Comparator.comparingInt((String item) -> item.length()).reversed());
        Collections.sort(list, Comparator.comparingInt(item -> item.length()));  //没有reversed竟然可以推断出来
        // 参数是? super T   这里传入的T是string,但是这里需要的是string及其父类----一般是本类和子类
        //public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> keyExtractor) {
        //        Objects.requireNonNull(keyExtractor);
        //        return (Comparator<T> & Serializable)
        //            (c1, c2) -> Integer.compare(keyExtractor.applyAsInt(c1), keyExtractor.applyAsInt(c2));
        //    }


        //default void sort(Comparator<? super E> c) {
        //        Object[] a = this.toArray();
        //        Arrays.sort(a, (Comparator) c);
        //        ListIterator<E> i = this.listIterator();
        //        for (Object e : a) {
        //            i.next();
        //            i.set((E) e);    //这里e可能是使用的自己或者是父类的方法进行的调用排序
        //        }
        //    }

    }
}
