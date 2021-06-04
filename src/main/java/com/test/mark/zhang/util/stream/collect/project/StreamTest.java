package com.test.mark.zhang.util.stream.collect.project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Classname StreamTest
 * @Description TODO
 * @Date 2021/6/4 9:09 上午
 * @Created by mark
 */
public class StreamTest {
    public static void main(String[] args) {
        List<WeakVO> lists =new ArrayList<>();
        Random random = new Random();
        int ri = random.nextInt();
        for (int i = 0; i < 100; i++) {
            WeakVO weakVO = new WeakVO();
            weakVO.setRiskLevel("");
            weakVO.setRiskType("");
            weakVO.setTotal(ri);
            lists.add(weakVO);
        }

        Map<String, Map<String, Integer>> collect = lists.stream().collect(Collectors.groupingBy(WeakVO::getRiskLevel,
                Collectors.groupingBy(WeakVO::getRiskType, Collectors.summingInt(WeakVO::getTotal))));
        System.out.println(".....");

        Integer total = lists.stream().max(Comparator.comparingInt(WeakVO::getTotal)).orElse(new WeakVO()).getTotal();
    }
}
