package com.test.mark.zhang.controller.other;

import lombok.Data;

import java.util.List;

/**
 * @Classname RequestBean
 * @Description TODO
 * @Date 2021/7/3 10:02 上午
 * @Created by mark
 */
@Data
public class RequestBean {
    private String name;
    private int age;
    private List<String> hobbys;

}
