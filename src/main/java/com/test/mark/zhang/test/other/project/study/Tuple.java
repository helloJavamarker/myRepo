package com.test.mark.zhang.test.other.project.study;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Classname Tuple
 * @Description TODO
 * @Date 2021/6/25 4:15 下午
 * @Created by mark
 */
@FunctionalInterface
public interface Tuple<K, V> {

    Object get(int i);

    static <K, V> Tuple<K, V> tuple(K k, V v) {
        return i -> i == 0 ? k : v;
    }

    default K getKey() {
        return (K) get(0);
    }

    default V getValue() {
        return (V) get(1);
    }

    static <K, V> Map<K, V> map(Tuple<K, V>... tuples) {
        return mapNew(s -> new HashMap<>(s, 1), tuples);
    }

    static <K, V> Map<K, V> mapNew(Function<Integer, ? extends Map<K, V>> creator, Tuple<K, V>... tuples) {
        return Arrays.stream(tuples).collect(Collectors.toMap(
                Tuple::getKey,
                Tuple::getValue,
                (a, b) -> b,
                () -> creator.apply(tuples.length)
        ));
    }
}
