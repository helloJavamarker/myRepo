package com.test.mark.zhang.test.other.project.aspect;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by mark
 * @Classname AspectController
 * @Description TODO
 * @Date 2021/7/12 7:39 下午
 */
@RestController()
@RequestMapping("aspect")
public class AspectController {
    public static List<String> data = new ArrayList<>();

    @PostConstruct
    public void init() {
        generateData();
        generateData2();
    }

    @GetMapping("/add")
    @IpSync(source = "add", method = SyncMethodEnum.ADD)
    public String update() {
//        System.out.println(data);
//        data.clear();
        System.out.println(data);
        generateData();
        generateData2();
//        add("wang");
//
        System.out.println(data);
        return "success";
    }

    public void clear() {
        data.clear();
    }

    public void add(String content) {
        data.add(content);
    }

    @IpSync(source = "add", method = SyncMethodEnum.ADD)
    public static void generateData() {
        data.add("zhang");
        data.add("san");
    }

    @IpSync(source = "add", method = SyncMethodEnum.ADD)
    private static void generateData2() {
        data.add("li");
        data.add("si");
    }


}
