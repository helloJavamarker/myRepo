package com.test.mark.zhang.test.other.project.cascade.anno2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by mark
 * @Classname DataSource
 * @Description TODO
 * @Date 2021/7/12 4:39 下午
 */
public class DataSource {

    private static List<String> data = new ArrayList<>();
    static  {
        data = searchFromDB();
    }
    @Cacheable("dataSource")
    public List<String> generateData() {
        data = Arrays.asList("zhang", "san", "li", "si");
        return data;
    }

    @CacheUpdate("datasource")
    public void add() {
        //data.clear();
        data = searchFromDB();
        //data.add("wang");
    }

    public static void main(String[] args) throws Exception {
        DataSource dataSource = new DataSource();
        //dataSource.add();
//        System.out.println(data);
        dataSource.filter();
    }

    private static List<String> searchFromDB() {
        List<String> list = new ArrayList<>();
        list.add("zhang");
        list.add("san");
        list.add("li");
        list.add("si");
        return list;
    }

    private void filter() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<DataSource> clazz = DataSource.class;
        for (Method method : clazz.getMethods()) {
            CacheUpdate annotation = method.getAnnotation(CacheUpdate.class);
            if (annotation != null) {
                DataSource instance = clazz.newInstance();
                System.out.println(data);
                method.invoke(instance, null);
                data.add("from filter");
                System.out.println(data);
            }
        }
    }
}
