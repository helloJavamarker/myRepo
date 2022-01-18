package com.test.mark.zhang.test.agency.shengsy.java8.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author by mark
 * @Classname PredicateTest
 * @Description TODO
 * @Date 2021/12/5 8:01 下午
 */
public class PredicateTest {

    private static Person person1 = new Person("zhangsan", 23);
    private static Person person2 = new Person("lisi", 30);
    private static Person person3 = new Person("wangwu", 40);
    private static Person person4 = new Person("zhaoliu", 50);
    private static List<Person> persons = Arrays.asList(person1, person2, person3, person4);

    public static void main(String[] args) {
        PredicateTest predicateTest = new PredicateTest();
        System.out.println(predicateTest.filterPersonByAge(30, persons));
        System.out.println(predicateTest.filterPersonByAge(29, persons));

        Predicate<String> predicate = str -> str.length() > 5;
        System.out.println(predicate.test("zhang"));
        System.out.println(predicate.test("zhangsan"));


        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        predicateTest.filterNum(nums, item -> item % 2 == 0);
    }

    private void filterNum(List<Integer> nums, Predicate<Integer> predicate) {
        for (Integer num : nums) {
            if (predicate.test(num)) {
                System.out.println(num);
            }
        }
    }

    /**
     * 只能满足特定需求,这里是age大于传入的值----重复出现多个地方调用根据age过滤
     *
     * @param age
     * @param persons
     * @return
     */
    private List<Person> filterPersonByAge(int age, List<Person> persons) {
        return persons.stream().filter(person -> person.getAge() > age).collect(Collectors.toList());
    }

    /**
     * 具体通过age怎么过滤,是大于,还是小于,还是小于等于,在调用的时候传入
     *
     * @param age
     * @param persons
     * @param biFunction
     * @return
     */
    private List<Person> filterPersonByAge(int age, List<Person> persons, BiFunction<Integer, List<Person>, List<Person>> biFunction) {
        return biFunction.apply(age, persons);
    }

}
