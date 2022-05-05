package com.test.mark.zhang.test.other.project.study;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.commons.lang3.tuple.Pair;

/**
 * @author by mark
 * @Classname PairTest
 * @Description TODO
 * @Date 2022/4/27 10:28 上午
 */
public class PairTest {
    public static void main(String[] args) {
        Pair<String, String> pair = Pair.of("zhang", "san");
        System.out.println(pair);
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());
        System.out.println(pair.getLeft());

        Triple<String, String, Integer> triple = new ImmutableTriple<>("张", "三", 23);
        System.out.println(triple.getLeft());
        System.out.println(triple.getMiddle());
        System.out.println(triple.getRight());
        System.out.println(triple);

        String result = "{" +"left"+ ":" + "right"+"}";
        //格式友好,是json格式
    }
}
