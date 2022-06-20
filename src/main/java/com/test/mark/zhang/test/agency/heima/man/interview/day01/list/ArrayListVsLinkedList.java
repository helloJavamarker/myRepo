package com.test.mark.zhang.test.agency.heima.man.interview.day01.list;

import org.openjdk.jol.info.ClassLayout;
import org.springframework.util.StopWatch;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.test.mark.zhang.test.agency.heima.man.interview.day01.list.TestArrayList.length;
import static com.test.mark.zhang.test.agency.heima.man.interview.day01.sort.Utils.randomArray;


@SuppressWarnings("all")
public class ArrayListVsLinkedList {
    public static void main(String[] args) throws InterruptedException {

//        testUseCurrentMills();
//        testUseCurrentStopWatch();

        int n = 1000;
        int insertIndex = n;
        for (int i = 0; i < 1; i++) {
            int[] array = randomArray(n);
            List<Integer> list1 = Arrays.stream(array).boxed().collect(Collectors.toList());
            LinkedList<Integer> list2 = new LinkedList<>(list1);

            randomAccess(list1, list2, n / 2);
            addFirst(list1,list2);
            addMiddle(list1, list2, n / 2);
            addLast(list1,list2);
            arrayListSize((ArrayList<Integer>) list1);
            linkedListSize(list2);
        }
    }

    static void linkedListSize(LinkedList<Integer> list) {
        try {
            long size = 0;
            ClassLayout layout = ClassLayout.parseInstance(list);
//            System.out.println(layout.toPrintable());
            size += layout.instanceSize();
            Field firstField = LinkedList.class.getDeclaredField("first");
            firstField.setAccessible(true);
            Object first = firstField.get(list);
//            System.out.println(ClassLayout.parseInstance(first).toPrintable());
            long nodeSize = ClassLayout.parseInstance(first).instanceSize();
            size += (nodeSize * (list.size() + 2));
            long elementSize = ClassLayout.parseInstance(list.getFirst()).instanceSize();
            System.out.println("LinkedList size:[" + size + "],per Node size:[" + nodeSize + "],per Element size:[" + elementSize * list.size() + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void arrayListSize(ArrayList<Integer> list) {
        try {
            long size = 0;
            ClassLayout layout = ClassLayout.parseInstance(list);
//            System.out.println(layout.toPrintable());
            size += layout.instanceSize();
            Field elementDataField = ArrayList.class.getDeclaredField("elementData");
            elementDataField.setAccessible(true);
            Object elementData = elementDataField.get(list);
//            System.out.println(ClassLayout.parseInstance(elementData).toPrintable());
            size += ClassLayout.parseInstance(elementData).instanceSize();
            long elementSize = ClassLayout.parseInstance(list.get(0)).instanceSize();
            System.out.println("ArrayList size:[" + size + "],array length:[" + length(list) + "],per Element size:[" + elementSize * list.size() + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void randomAccess(List<Integer> list1, LinkedList<Integer> list2, int mid) {
        StopWatch sw = new StopWatch();
        sw.start("ArrayList");
        list1.get(mid);
        sw.stop();

        sw.start("LinkedList");
        list2.get(mid);
        sw.stop();

        System.out.println(sw.prettyPrint());
    }

    private static void addMiddle(List<Integer> list1, LinkedList<Integer> list2, int mid) {
        StopWatch sw = new StopWatch();
        sw.start("ArrayList");
        list1.add(mid, 100);
        sw.stop();

        sw.start("LinkedList");
        list2.add(mid, 100);
        sw.stop();

        System.out.println(sw.prettyPrint());
    }

    private static void addFirst(List<Integer> list1, LinkedList<Integer> list2) {
        StopWatch sw = new StopWatch();
        sw.start("ArrayList");
        list1.add(0, 100);
        sw.stop();

        sw.start("LinkedList");
        list2.addFirst(100);
        sw.stop();

        System.out.println(sw.prettyPrint());
    }

    private static void addLast(List<Integer> list1, LinkedList<Integer> list2) {
        StopWatch sw = new StopWatch();
        sw.start("ArrayList");
        list1.add(100);
        sw.stop();

        sw.start("LinkedList");
        list2.add(100);
        sw.stop();

        System.out.println(sw.prettyPrint());
    }


    static void testUseCurrentMills() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end1 = System.currentTimeMillis();
        System.out.println("代码块1执行时间" + (end1 - start));
        Thread.sleep(2000);
        long end2 = System.currentTimeMillis();
        System.out.println("代码块2执行时间" + (end2 - end1));
        Thread.sleep(3000);
        long end3 = System.currentTimeMillis();
        System.out.println("代码块3执行时间" + (end3 - end2));
        System.out.println("总共执行时间" + (end3 - start));

        //代码块1执行时间1000
        //代码块2执行时间2001
        //代码块3执行时间3000
        //总共执行时间6001

    }

    static void testUseCurrentStopWatch() throws InterruptedException {
        StopWatch stopWatch = new StopWatch("测试代码块组");
        stopWatch.start("代码块1");
        Thread.sleep(1000);
        stopWatch.stop();
        stopWatch.start("代码块2");
        Thread.sleep(2000);
        stopWatch.stop();
        stopWatch.start("代码块3");
        Thread.sleep(3000);
        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());


        org.apache.commons.lang3.time.StopWatch stopWatch1 = new org.apache.commons.lang3.time.StopWatch();

        //StopWatch '测试代码块组': running time (millis) = 6002
        //-----------------------------------------
        //ms     %     Task name
        //-----------------------------------------
        //01001  017%  代码块1
        //02000  033%  代码块2
        //03001  050%  代码块3
    }

}
