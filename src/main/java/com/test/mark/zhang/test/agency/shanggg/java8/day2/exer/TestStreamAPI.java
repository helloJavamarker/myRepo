package com.test.mark.zhang.test.agency.shanggg.java8.day2.exer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.mark.zhang.test.agency.shanggg.java8.day2.java8.Employee;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.junit.Test;
import org.springframework.util.DigestUtils;

public class TestStreamAPI {

    /*
          1.	给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
        ，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】。
     */
    public static void test1() {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};

        Arrays.stream(nums)
                .map((x) -> x * x)
                .forEach(System.out::println);
    }

    /*
     2.	怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
     */
    static List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    public static void test2() {
        Optional<Integer> count = emps.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);

        System.out.println(count.get());
    }

    @Test
    public void test() {
        System.out.println(QueryParser.escape("1.1.1.2,1.1.1"));


        // es处理特殊字符   escape
        String query = "。、/}";
        System.out.println("queryOrg=" + query);
        query = QueryParser.escape(query);
        System.out.println("queryEnd=" + query);

        emps.stream().sorted(Comparator.comparingInt(Employee::getId)).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("//////");
        new ArrayList<Employee>().stream().sorted(Comparator.comparingInt(Employee::getId)).collect(Collectors.toList()).forEach(System.out::println);
        System.out.println("/////");

        String json = "{\"name\": \"\", \"age\": 23}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        System.out.println(jsonObject.getInteger("name"));

//        System.out.println(jsonObject.getString("name"));
//        System.out.println(jsonObject.getInteger("name"));
//        System.out.println(jsonObject.getInteger("age") );
//        System.out.println(jsonObject.getInteger("age") == 23);
//        System.out.println(jsonObject.getInteger("age2") == 23);
//        System.out.println(jsonObject.getString("name2"));
    }

    public static void main(String[] args) {
        String json1 = "{\"list\":[{\"count\":\"590\",\"id\":1091,\"name\":\"Trojan/Macro.Office.Thus.I\",\"ranking\":1},{\"count\":\"356\",\"id\":522,\"name\":\"Adware.Agent.c4606b2a\",\"ranking\":2},{\"count\":\"327\",\"id\":295,\"name\":\"Virus.Win32.Ramnit.B\",\"ranking\":3},{\"count\":\"120\",\"id\":481,\"name\":\"Trojan.Agent.b118b61d\",\"ranking\":4},{\"count\":\"84\",\"id\":6,\"name\":\"Trojan.Generic\",\"ranking\":5},{\"count\":\"49\",\"id\":1014,\"name\":\"Adware.Agent.637860e7\",\"ranking\":6},{\"count\":\"35\",\"id\":292,\"name\":\"Html.Win32.Dropper.A\",\"ranking\":7},{\"count\":\"32\",\"id\":368,\"name\":\"Trojan.Agent.5e2dd1b0\",\"ranking\":8},{\"count\":\"32\",\"id\":399,\"name\":\"Trojan.Agent.0116bff5\",\"ranking\":9},{\"count\":\"30\",\"id\":298,\"name\":\"Macro.Office.Marker.G\",\"ranking\":10}],\"status\":\"ok\"}";
        JSONObject jsonObject1 = JSONObject.parseObject(json1);
        JSONArray jsonArray = jsonObject1.getJSONArray("list");
        String o = jsonArray.get(0).toString();
        System.out.println(o);
        JSONObject jsonObject2 = JSONObject.parseObject(o);
        Integer count = 0;
        count += jsonObject2.getInteger("count");
        System.out.println(count);
        System.out.println();

        String str = "zhang,san,li,si";
        String[] split = StringUtils.split(str, ",");

        String uuid = DigestUtils.md5DigestAsHex(LocalDateTime.now().toString().getBytes());
        System.out.println(uuid);
        //返回给前端,下次带上这个UUID可以直接从缓存拿数据
        //setCache(uuid, data);
        String content = "zhangzhangzhang san";
        System.out.println(StringUtils.replace(content, "zhg", ""));


        String json = "{\"name\":\"zhangsan\", \"age\":23}";
        JSONObject jsonObject = JSONObject.parseObject(json);
//        System.out.println(jsonObject.getInteger("age"));
        System.out.println(jsonObject.getString("age"));
        System.out.println(jsonObject.getString("name"));
        System.out.println("zhangsan".equals(jsonObject.getString("name")));
        System.out.println("zhangsan".equals(jsonObject.getString("name2")));
        System.out.println(jsonObject.getString("name"));

        System.out.println(jsonObject.getInteger("age2"));
        int a = 0;
        a += jsonObject.getInteger("age22");

    }
}
