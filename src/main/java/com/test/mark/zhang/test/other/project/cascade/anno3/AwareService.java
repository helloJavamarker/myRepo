package com.test.mark.zhang.test.other.project.cascade.anno3;

import com.test.mark.zhang.test.other.project.cascade.anno2.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by mark
 * @Classname AwareService
 * @Description TODO
 * @Date 2021/7/12 6:41 下午
 */
@Service
public class AwareService {

    private static List<String> data = new ArrayList<>();

    @Cacheable("dataSource")
    @PostConstruct
    public List<String> generateData() {
        data = searchFromDB();
        return data;
    }


    private static List<String> searchFromDB() {
        List<String> list = new ArrayList<>();
        list.add("zhang");
        list.add("san");
        list.add("li");
        list.add("si");
        return list;
    }
}
