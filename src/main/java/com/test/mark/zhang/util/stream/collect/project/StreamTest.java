package com.test.mark.zhang.util.stream.collect.project;

import com.test.mark.zhang.entity.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Classname StreamTest
 * @Description TODO
 * @Date 2021/6/4 9:09 上午
 * @Created by mark
 */
public class StreamTest {
    public static void main(String[] args) {

        //generateData().stream().filter()
        OrgBean bean = new OrgBean(401L, "滨江", "401xx", "300xx");
//        OrgBean bean = new OrgBean(401L, "滨江", "503xx", "502xx");
//        OrgBean bean = new OrgBean(401L, "滨江", "201xx", "101xx");
        //System.out.println(getBranch(bean41, generateData()));
        List<OrgBean> orgBeans = generateData();
        System.out.println(getBranch(bean.getOrgId()));

    }


    private static List<OrgBean> generateData() {
        /*OrgBean bean0 = new OrgBean(100L, "总部", "100xx", null,null);
        OrgBean bean1 = new OrgBean(101L, "直属", "101xx", "100xx",bean0);
        OrgBean bean11 = new OrgBean(102L, "分支机构1", "102xx", "100xx",bean0);
        OrgBean bean2 = new OrgBean(103L, "分支机构2", "103xx", "100xx",bean0);
        OrgBean bean222 = new OrgBean(200L, "浙江", "200xx", "101xx",bean1);
        OrgBean bean20 = new OrgBean(201L, "广东", "201xx", "1001xx",bean1);
        OrgBean bean22 = new OrgBean(300L, "杭州", "300xx", "200xx",bean222);
        OrgBean bean3 = new OrgBean(301L, "宁波", "301xx", "200xx",bean222);
        OrgBean bean4 = new OrgBean(400L, "西湖", "400xx", "300xx",bean22);
        OrgBean bean41 = new OrgBean(401L, "滨江", "401xx", "300xx",bean22);
        OrgBean bean51 = new OrgBean(501L, "局域1", "501xx", "102xx",bean11);
        OrgBean bean52 = new OrgBean(502L, "局域2", "502xx", "102xx",bean11);
        OrgBean bean53 = new OrgBean(503L, "局域11", "503xx", "502xx",bean51);
        OrgBean bean54 = new OrgBean(504L, "局域21", "504xx", "502xx",bean51);*/

        OrgBean bean0 = new OrgBean(100L, "总部", "100xx", null);
        OrgBean bean1 = new OrgBean(101L, "直属", "101xx", "100xx");
        OrgBean bean11 = new OrgBean(102L, "分支机构1", "102xx", "100xx");
        OrgBean bean2 = new OrgBean(103L, "分支机构2", "103xx", "100xx");
        OrgBean bean222 = new OrgBean(200L, "浙江", "200xx", "101xx");
        OrgBean bean20 = new OrgBean(201L, "广东", "201xx", "101xx");
        OrgBean bean22 = new OrgBean(300L, "杭州", "300xx", "200xx");
        OrgBean bean3 = new OrgBean(301L, "宁波", "301xx", "200xx");
        OrgBean bean4 = new OrgBean(400L, "西湖", "400xx", "300xx");
        OrgBean bean41 = new OrgBean(401L, "滨江", "401xx", "300xx");
        OrgBean bean51 = new OrgBean(501L, "局域1", "501xx", "102xx");
        OrgBean bean52 = new OrgBean(502L, "局域2", "502xx", "102xx");
        OrgBean bean53 = new OrgBean(503L, "局域11", "503xx", "502xx");
        OrgBean bean54 = new OrgBean(504L, "局域21", "504xx", "502xx");

        List<OrgBean> beans = Arrays.asList(bean0, bean1, bean11, bean2, bean222, bean20, bean22, bean3, bean4, bean41, bean51, bean52, bean53, bean54);
        return beans;
    }

    private static OrgBean getBranch(String orgId) {
        for (OrgBean bean : generateData()) {
//            if ("100xx".equals(bean.getParentOrgId())) {
//                return bean;
//            }
            if (orgId.equals(bean.getOrgId())) {
                if (!"100xx".equals(bean.getParentOrgId())) {
                    return getBranch(bean.getParentOrgId());
                } else {
                    return bean;
                }
            }

        }
        return null;
    }

    private static void method1() {
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

        List<Person> persons =new ArrayList<>();
        persons.add(new Person("zhang",23,"play",100L));
        persons.add(new Person("zhang1",24,"play1",1010L));
        persons.add(new Person("zhang2",27,"play1",102L));
        persons.add(new Person("zhang3",13,"play3",103L));
        persons.add(new Person("zhang4",23,"play",104L));
        persons.add(new Person("zhang5",23,"play",104L));
        persons.add(new Person("zhang6",23,"play",104L));

        TreeMap<String, Person> collect1 = persons.stream().collect(Collectors.toMap(Person::getName, Function.identity(), (o1, o2) -> o1, TreeMap::new));
        System.out.println(collect1);
        //按hobby分组
        Map<String, List<Person>> collect2 = persons.stream().collect(Collectors.groupingBy(Person::getHobby));
        System.out.println(collect2);

        //将数据分为两部分:男女性别、成绩及格与否等
        Map<Boolean, List<Person>> collect3 = persons.stream().collect(Collectors.partitioningBy(p -> p.getAge() > 20));
        System.out.println(collect3);
    }

}
