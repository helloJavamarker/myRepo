package com.test.mark.zhang.test.other.jackson;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by mark
 * @Classname ObjectMapperTest
 * @Description TODO
 * @Date 2021/8/11 1:27 下午
 */
@Slf4j(topic = "t11111")
public class ObjectMapperTest {
    /**
     * Jackson ObjectMapper如何将JSON字段与Java字段匹配
     * 三种方式
     * 1.Jackson通过将JSON字段的名称与Java对象中的getter和setter方法相匹配，将JSON对象的字段映射到Java对象中的字段。Jackson删除了getter和setter方法名称的“get”和“set”部分，并将剩余名称的第一个字符转换为小写。
     * 2.Jackson还可以通过java反射进行匹配
     * 3.通过注解或者其它方式进行自定义的序列化和反序列化程序。
     * https://www.jianshu.com/p/67b6da565f81
     */
    @Test
    public void test01() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        Car car = objectMapper.readValue(carJson, Car.class);
    }

    @Test
    public void test02() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 4 }";
        log.debug("........");
        Reader reader = new StringReader(carJson);
        Car car = objectMapper.readValue(reader, Car.class);
    }

    @Test
    public void test03() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("data/car.json");
        Car car = objectMapper.readValue(file, Car.class);
    }

    @Test
    public void test04() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        URL url = new URL("file:data/car.json");
        Car car = objectMapper.readValue(url, Car.class);
        //本例使用的是文件URL，也可使用一个HTTP URL（如：http://jenkov.com/some-data.json ).
    }

    @Test
    public void test05() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream input = new FileInputStream("data/car.json");
        Car car = objectMapper.readValue(input, Car.class);
    }

    @Test
    public void test06() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        byte[] bytes = carJson.getBytes("UTF-8");
        Car car = objectMapper.readValue(bytes, Car.class);
    }

    @Test
    public void test07() throws IOException {
        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        Car[] cars2 = objectMapper.readValue(jsonArray, Car[].class);
    }

    @Test
    public void test08() throws IOException {
        String jsonArray = "[{\"brand\":\"ford\"}, {\"brand\":\"Fiat\"}]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<Car> cars1 = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>() {
        });
    }


    @Test
    public void test09() throws IOException {
        String jsonObject = "{\"brand\":\"ford\", \"doors\":5}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = objectMapper.readValue(jsonObject,
                new TypeReference<Map<String, Object>>() {
                });
    }


    /**
     * 转Json
     * ObjectMapper write有三个方法
     * <p>
     * writeValue()
     * writeValueAsString()
     * writeValueAsBytes()
     *
     * @throws IOException
     */


    @Test
    public void test10() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car();
        car.setBrand("BMW");
        car.setDoors(4);
        //写到文件中
        objectMapper.writeValue(new FileOutputStream("data/output-2.json"), car);
        //写到字符串中
        String json = objectMapper.writeValueAsString(car);
    }


    /**
     * YAML是一种文本数据格式，类似于JSON，但使用不同的语法。Jackson ObjectMapper可以像读写JSON一样读写YAML。为了使用Jackson读取和写入YAML，您需要为项目添加额外的Maven依赖项
     * @throws IOException
     */
    @Test
    public void test11() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        Employee employee = new Employee("John Doe", "john@doe.com");

        String yamlString = null;
        try {
            yamlString = objectMapper.writeValueAsString(employee);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // normally, rethrow exception here - or don't catch it at all.
        }

        try {
            Employee employee2 = objectMapper.readValue(yamlString, Employee.class);
            System.out.println("employee2 = " + employee2);
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在使用fastJson时,对于泛型的反序列化很多场景下都会使用到TypeReference，例如
     * 使用TypeReference可以明确的指定反序列化的类型，具体实现逻辑参考TypeReference的构造函数
     */

    @Test
    public void testTypeReference() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(9);
        list.add(4);
        list.add(8);
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("a", list);
        System.out.println(jsonObj);

//        List<String> list2 = jsonObj.getObject("a", new TypeReference<List<Integer>>(){});
//        System.out.println(list2);

        //Type[] getActualTypeArguments(); //返回泛型类型数组
        //Type getRawType(); //返回原始类型Type
        //Type getOwnerType(); //返回 Type 对象，表示此类型是其成员之一的类型。
    }


    public static class IntMap extends HashMap<String, Integer> {}

    static void test1() {
        IntMap intMap = new IntMap();
        System.out.println("getSuperclass:" + intMap.getClass().getSuperclass());
        System.out.println("getGenericSuperclass:" + intMap.getClass().getGenericSuperclass());
        Type type = intMap.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType)type;
            for (Type t : p.getActualTypeArguments()) {
                System.out.println(t);
            }
        }
    }

    static void test2() {
        Map<String, Integer> intMap = new HashMap<>();
        System.out.println("\ngetSuperclass:" + intMap.getClass().getSuperclass());
        System.out.println("getGenericSuperclass:" + intMap.getClass().getGenericSuperclass());
        Type type = intMap.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType)type;
            for (Type t : p.getActualTypeArguments()) {
                System.out.println(t);
            }
        }
    }

    static void test3() {
        Map<String, Integer> intMap = new HashMap<String, Integer>(){};
        System.out.println("\ngetSuperclass:" + intMap.getClass().getSuperclass());
        System.out.println("getGenericSuperclass:" + intMap.getClass().getGenericSuperclass());
        Type type = intMap.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType p = (ParameterizedType)type;
            for (Type t : p.getActualTypeArguments()) {
                System.out.println(t);
            }
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }

}
