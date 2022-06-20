package com.test.mark.zhang.test.agency.heima.man.interview.day01.map;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.test.mark.zhang.test.agency.heima.man.interview.day01.sort.Utils;
import lombok.Data;
import org.junit.Test;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

// --add-opens java.base/java.util=ALL-UNNAMED
public class DistributionAffectedByCapacity {
    // .net 是原始容量 * 2 开始找下一个质数作为新容量
    public static void main(String[] args) {
        System.out.println(hashtableGrowRule(10));
//        int[] array = Utils.randomArray(1000); // 足够随机
        int[] array = Utils.lowSameArray(1000);
//        int[] array = Utils.evenArray(1000);
        System.out.println(Arrays.toString(array));
        int[] sizes = {11, 16, 23};
        printHashResult(array, sizes);
    }

    public static void printHashResult(int[] array, int[] sizes) {
        List<Map<Integer, AtomicInteger>> maps = new ArrayList<>();
        for (int size : sizes) {
            maps.add(getMap(size));
        }
        for (int hash : array) {
            for (int j = 0; j < sizes.length; j++) {
                maps.get(j).get(hash % sizes[j]).incrementAndGet();
            }
        }
        for (Map<Integer, AtomicInteger> map : maps) {
            System.out.printf("size:[%d] %s%n", map.size(), map);
        }
    }

    private static HashMap<Integer, AtomicInteger> getMap(int size) {
        HashMap<Integer, AtomicInteger> result = new HashMap<>();
        for (int i = 0; i < size; i++) {
            result.put(i, new AtomicInteger());
        }
        return result;
    }

    private static List<Integer> hashtableGrowRule(int n) {
        List<Integer> list = new ArrayList<>();
        int init = 0;
        list.add(init);
        if (n >= 1) {
            init = 11;
            list.add(init);
        }
        for (int i = 1; i < n; i++) {
            init = (init << 1) + 1;
            list.add(init);
        }
        return list;
    }

    @Test
    public void test() {
        System.out.println(NumberUtil.div(0.1, 38, 4) * 100);
        List<Entity> list = new ArrayList<>();
        System.out.println(list.stream().collect(Collectors.averagingDouble(Entity::getPrice)));
        System.out.println(list.stream().collect(Collectors.averagingDouble(Entity::getAge)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format = LocalDateTime.now().plusDays(-1).format(formatter);


        System.out.println(DateUtil.offsetMinute(new Date(), -2));
    }

    @Data
    static class Entity {
        private Double price;
        private double age;
    }
}
